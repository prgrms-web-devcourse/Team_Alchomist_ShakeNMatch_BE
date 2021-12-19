package com.shake_match.alchomist.users.service;

import static com.google.common.base.Preconditions.checkArgument;
import static org.hibernate.internal.util.StringHelper.isNotEmpty;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.CocktailIngredient;
import com.shake_match.alchomist.cocktail.dto.CocktailSimpleListResponse;
import com.shake_match.alchomist.cocktail.dto.CocktailSimpleResponse;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.group.domain.Group;
import com.shake_match.alchomist.group.repository.GroupRepository;
import com.shake_match.alchomist.ingredient.Ingredient;
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
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.users.dto.response.UserNicknameResponse;
import com.shake_match.alchomist.users.dto.response.UserUpdateResponse;
import com.shake_match.alchomist.users.repository.UserIngredientRepository;
import com.shake_match.alchomist.users.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CocktailRepository cocktailRepository;

    private final IngredientRepository ingredientRepository;

    private final UserConverter userConverter;

    private final GroupRepository groupRepository;
    private final IngredientConverter ingredientConverter;
    private final UserIngredientRepository userIngredientRepository;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Transactional
    public UserUpdateResponse updateById(Long userId, UserUpdateRequest userUpdateRequest)
        throws Exception {
        Users changedUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        changedUser.update(userUpdateRequest);
        return userConverter.toUserUpdateResponse(changedUser);
    }

    @Transactional
    public UserDetailResponse getUserDetail(Long id) {
        Users user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));

        List<Cocktail> cocktailByBookmark = user.getCocktails();

        List<UserBookmarkResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktailByBookmark) {
            responses.add(userConverter.toSearchByBookmark(cocktail));
        }

        return userConverter.toUserResponse(user, responses);
    }

    @Transactional
    public IngredientToListResponse getUserByIngredient(Long id) {
        Users user = userRepository.findById(id)
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
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));

        userIngredientRepository.deleteUsersIdAndIngredient(user.getId());

        List<UsersIngredient> usersIngredients = ingredientIds.stream()
            .map(ingredientId -> ingredientRepository.getById(ingredientId))
            .map(ingredient -> new UsersIngredient(user, ingredient))
            .collect(Collectors.toList());
        userIngredientRepository.saveAll(usersIngredients);
    }


    @Transactional
    public void deletedIngredientOfUser(Long userId, List<Long> ingredientIds) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        userIngredientRepository.deleteUsersIdAndIngredientIds(userId, ingredientIds);
    }


    @Transactional
    public Users getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
    }

    @Transactional
    public UserNicknameResponse getUserByNickname(String nickname) {
        boolean can = userRepository.findByNickname(nickname).isEmpty();
        return userConverter.toUserNicknameResponse(can);
    }

    @Transactional
    public void addBookmark(Long userId, Long cocktailId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(cocktailId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        user.addCocktails(cocktail);
    }

    @Transactional
    public void deleteBookmark(UserBookmarkRequest userBookmarkRequest) {
        Users user = userRepository.findById(userBookmarkRequest.getUserId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(userBookmarkRequest.getCocktailId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        user.addCocktails(cocktail);
    }

    @Transactional(readOnly = true)
    public List<UserBookmarkResponse> getBookmarkById(Long userId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        List<Cocktail> cocktailByBookmark = user.getCocktails();

        List<UserBookmarkResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktailByBookmark) {
            responses.add(userConverter.toSearchByBookmark(cocktail));
        }
        return responses;
    }

    @Transactional
    public Users join(OAuth2User oauth2User, String authorizedClientRegistrationId) {
        checkArgument(oauth2User != null, "oauth2User must be provided.");
        checkArgument(isNotEmpty(authorizedClientRegistrationId),
            "authorizedClientRegistrationId must be provided.");

        String providerId = oauth2User.getName();
        return findByProviderAndProviderId(authorizedClientRegistrationId, providerId)
            .map(user -> {
                log.warn("Already exists: {} for (provider: {}, providerId: {})", user,
                    authorizedClientRegistrationId, providerId);
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
                    .orElseThrow(
                        () -> new IllegalStateException("Could not found group for USER_GROUP"));
                Users user = new Users(nickname, authorizedClientRegistrationId, providerId,
                    profileImage, group);
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

    @Transactional
    public UserDetailResponse addJoinInfo(String username, UserJoinRequest userJoinRequest)
        throws Exception {
        Users users = userRepository.findByUsername(username).get();
        users.setOtherInfo(userJoinRequest.getNickname(),
            userJoinRequest.getIsMan(),
            userJoinRequest.getAge(),
            userJoinRequest.getMbti());
        return userConverter.toUserResponse(users);
    }

    @Transactional(readOnly = true)
    public UserDetailResponse searchByToken(String username) throws Exception {
        Optional<Users> users = userRepository.findByUsername(username);
        if (users.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<Cocktail> cocktailByBookmark = users.get().getCocktails();
        List<UserBookmarkResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktailByBookmark) {
            responses.add(userConverter.toSearchByBookmark(cocktail));
        }

        return userConverter.toUserResponse(users.get(), responses);
    }
}
