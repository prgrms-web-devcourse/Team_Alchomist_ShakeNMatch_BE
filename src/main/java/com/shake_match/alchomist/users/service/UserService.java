package com.shake_match.alchomist.users.service;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.CocktailIngredient;
import com.shake_match.alchomist.cocktail.dto.CocktailSimpleListResponse;
import com.shake_match.alchomist.cocktail.dto.CocktailSimpleResponse;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.group.domain.Group;
import com.shake_match.alchomist.group.repository.GroupRepository;
import com.shake_match.alchomist.ingredient.converter.IngredientConverter;
import com.shake_match.alchomist.ingredient.dto.response.IngredientListResponse;
import com.shake_match.alchomist.ingredient.dto.response.IngredientToListResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.UsersIngredient;
import com.shake_match.alchomist.users.converter.UserConverter;
import com.shake_match.alchomist.users.dto.request.UserBookmarkRequest;
import com.shake_match.alchomist.users.dto.request.UserJoinRequest;
import com.shake_match.alchomist.users.dto.request.UserUpdateRequest;
import com.shake_match.alchomist.users.dto.response.*;
import com.shake_match.alchomist.users.repository.UserRepository;
import com.shake_match.alchomist.users.repository.UserIngredientRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.checkArgument;
import static org.hibernate.internal.util.StringHelper.isNotEmpty;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CocktailRepository cocktailRepository;

    private final IngredientRepository ingredientRepository;

    private final UserConverter userConverter;

    private final GroupRepository groupRepository;

    private Logger log = LoggerFactory.getLogger(getClass());

    private final IngredientConverter ingredientConverter;

    private final UserIngredientRepository userIngredientRepository;

    @Transactional
    public UserUpdateResponse updateById(Long userId, UserUpdateRequest userUpdateRequest)
        throws Exception {
        Users changedUser = userRepository.findByProviderId(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        changedUser.update(userUpdateRequest);
        return userConverter.toUserUpdateResponse(changedUser);
    }

    @Transactional
    public UserDetailResponse getUserDetail(Long id) {
        Users user = userRepository.findByProviderId(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        return userConverter.toUserResponse(user);
    }

    @Transactional
    public IngredientToListResponse getUserByIngredient(Long id) {
        Users user = userRepository.findByProviderId(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        List<IngredientListResponse> ingredientListResponseList =
            user.getUsersIngredient().stream()
                .map(UsersIngredient::getIngredient)
                .map(ingredientConverter::converterIngredientListResponse)
                .collect(Collectors.toList());
        return ingredientConverter.converterIngredientToListResponse(ingredientListResponseList);
    }

    @Transactional
    public CocktailSimpleListResponse getAllCocktailByIngredient(List<Long> ingredientId) {
        List<CocktailIngredient> cocktailIngredients = cocktailRepository.findAllByIngredients(
            ingredientId);

        Map<Long, Cocktail> map = new HashMap<>();
        for (CocktailIngredient cocktailIngredient : cocktailIngredients) {
            Long key = cocktailIngredient.getCocktail().getId();
            if (!map.containsKey(key)) {
                map.put(key, cocktailIngredient.getCocktail());
            }
        }
        List<CocktailSimpleResponse> cocktails = map.values()
            .stream()
            .map(CocktailSimpleResponse::new)
            .collect(Collectors.toList());
        return new CocktailSimpleListResponse(cocktails);
    }

    @Transactional
    public void saveIngredientOfUser(Long userId, List<Long> ingredientIds) {
        Users user = userRepository.findByProviderId(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));

        List<UsersIngredient> usersIngredients = ingredientIds.stream()
            .map(ingredientId -> ingredientRepository.getById(ingredientId))
            .map(ingredient -> new UsersIngredient(user, ingredient))
            .collect(Collectors.toList());
        userIngredientRepository.saveAll(usersIngredients);
    }


    @Transactional
    public void deletedIngredientOfUser(Long userId, List<Long> ingredientIds) {
        Users user = userRepository.findByProviderId(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        userIngredientRepository.deleteUsersIdAndIngredientIds(userId, ingredientIds);
    }


    @Transactional
    public Users getUserById(Long userId) {
        return userRepository.findByProviderId(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
    }

    @Transactional
    public UserNicknameResponse getUserByNickname(String nickname) {
        boolean can = userRepository.findByNickname(nickname).isEmpty();
        return userConverter.toUserNicknameResponse(can);
    }

    @Transactional
    public UserLikeResponse addBookmark(Long userId, Long cocktailId) {
        Users user = userRepository.findByProviderId(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(cocktailId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        user.addCocktails(cocktail);
        return new UserLikeResponse(user);
    }

    @Transactional
    public UserLikeResponse deleteBookmark(UserBookmarkRequest userBookmarkRequest) {
        Users user = userRepository.findByProviderId(userBookmarkRequest.getUserId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(userBookmarkRequest.getCocktailId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        user.addCocktails(cocktail);
        return new UserLikeResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserBookmarkResponse> getBookmarkById(Long userId) {
        Users user = userRepository.findByProviderId(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        List<Cocktail> cocktailByBookmark = user.getCocktails();

        if (cocktailByBookmark.isEmpty()) {
            throw new NotFoundException(ErrorCode.NOT_EXIST_BOOKMARK);
        }

        List<UserBookmarkResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktailByBookmark) {
            responses.add(userConverter.toSearchByBookmark(cocktail));
        }
        return responses;
    }

    @Transactional
    public Users join(OAuth2User oauth2User, String authorizedClientRegistrationId) {
        checkArgument(oauth2User != null, "oauth2User must be provided.");
        checkArgument(isNotEmpty(authorizedClientRegistrationId), "authorizedClientRegistrationId must be provided.");

        String providerId = oauth2User.getName();
        return findByProviderAndProviderId(authorizedClientRegistrationId, providerId)
                .map(user -> {
                    log.warn("Already exists: {} for (provider: {}, providerId: {})", user, authorizedClientRegistrationId, providerId);
                    return user;
                })
                .orElseGet(() -> {
                    Map<String, Object> attributes = oauth2User.getAttributes();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
                    checkArgument(properties != null, "OAuth2User properties is empty");

                    String nickname = (String) properties.get("nickname");
                    String profileImage = (String) properties.get("profile_image");
                    Group group = groupRepository.findByName("USER_GROUP")
                            .orElseThrow(() -> new IllegalStateException("Could not found group for USER_GROUP"));
                    Users user = new Users(nickname, authorizedClientRegistrationId, providerId, profileImage, group);
                    user.setOtherInfo("", true, 20, "");
                    System.out.println(providerId);
                    return userRepository.save(user);
                });
    }

    @Transactional(readOnly = true)
    public Optional<Users> findByUsername(String username) {
        checkArgument(isNotEmpty(username), "username must be provided.");

        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Users> findByProviderAndProviderId(String provider, String providerId) {
        checkArgument(isNotEmpty(provider), "provider must be provided.");
        checkArgument(isNotEmpty(providerId), "providerId must be provided.");

        return userRepository.findByProviderAndProviderId(provider, providerId);
    }

    public UserDetailResponse addJoinInfo(String username, UserJoinRequest userJoinRequest) throws Exception{
        Optional<Users> users = userRepository.findByUsername(username);
        if (users.isEmpty()){
            throw new EntityNotFoundException();
        }
        users.get().setOtherInfo(userJoinRequest.getNickname(), userJoinRequest.isMan(), userJoinRequest.getAge(), userJoinRequest.getMbti());
        return userConverter.toUserResponse(users.get());
    }

    public UserDetailResponse searchByToken(String username) throws Exception{
        Optional<Users> users = userRepository.findByUsername(username);
        if (users.isEmpty()){
            throw new EntityNotFoundException();
        }
        return userConverter.toUserResponse(users.get());
    }
}
