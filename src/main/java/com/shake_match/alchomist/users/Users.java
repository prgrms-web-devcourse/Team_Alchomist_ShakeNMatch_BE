package com.shake_match.alchomist.users;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.users.dto.request.UserUpdateRequest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String email;

    @Column(nullable = true)
    String password;

    @Column
    String nickname;

    @Column
    String imageUrl;

    @Column(nullable = false)
    boolean isMan;

    @Range(min = 20)
    @Column(nullable = false)
    int age;

    @Column
    String mbti;

    @OneToMany(mappedBy = "users")
    List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<UsersIngredient> usersIngredient = new ArrayList<>();

    @OneToMany
    List<Cocktail> cocktails = new ArrayList<>();

    public Users(String email, String nickname, String imageUrl, boolean gender, int age,
                 String mbti) {
        this.email = email;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.isMan = gender;
        this.age = age;
        this.mbti = mbti;
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
