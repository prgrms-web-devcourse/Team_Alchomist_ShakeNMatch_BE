package com.shake_match.alchomist.users;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.group.domain.Group;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.users.dto.request.UserUpdateRequest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "nickname")
    private String nickname;

    @Column(nullable = false)
    boolean isMan;

    @Range(min = 20)
    @Column(nullable = false)
    int age;

    @Column
    String mbti;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "users")
    List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UsersIngredient> usersIngredient = new ArrayList<>();

    @OneToMany
    List<Cocktail> cocktails = new ArrayList<>();

    public void setOtherInfo(String nickname, boolean gender, int age, String mbti) {
        this.nickname = nickname;
        this.isMan = gender;
        this.age = age;
        this.mbti = mbti;
    }

    public Users(String username, String provider, String providerId, String profileImage, Group group) {
        checkArgument(isNotEmpty(username), "username must be provided.");
        checkArgument(isNotEmpty(provider), "provider must be provided.");
        checkArgument(isNotEmpty(providerId), "providerId must be provided.");
        checkArgument(group != null, "group must be provided.");

        this.username = username;
        this.provider = provider;
        this.providerId = providerId;
        this.profileImage = profileImage;
        this.group = group;
    }

    public void addCocktails(Cocktail cocktail) {
        if (!this.getCocktails().contains(cocktail)) {
            cocktails.add(cocktail);
            cocktail.addLikes(true);
        } else {
            cocktails.remove(cocktail);
            cocktail.addLikes(false);
        }
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        this.nickname = userUpdateRequest.getNickname();
        this.isMan = userUpdateRequest.isMan();
        this.age = userUpdateRequest.getAge();
        this.mbti = userUpdateRequest.getMbti();
    }

}
