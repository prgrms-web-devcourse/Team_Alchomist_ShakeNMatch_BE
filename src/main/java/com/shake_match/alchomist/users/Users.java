package com.shake_match.alchomist.users;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String password;

    @Column
    String nickname;

    @Column
    String imageUrl;

    @Column(nullable = false)
    boolean gender;

    @Size(min = 20)
    @Column(nullable = false)
    int age;

    @Column
    String mbti;

    @OneToMany(mappedBy = "users")
    List<Review> reviews = new ArrayList<>();

    @OneToMany
    List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany
    List<Cocktail> cocktails = new ArrayList<>();

    public void addCocktails(Cocktail cocktail) {
        if(!this.getCocktails().contains(cocktail)){
            cocktails.add(cocktail);
            cocktail.addLikes(true);
        }
        else {
            cocktails.remove(cocktail);
            cocktail.addLikes(false);
        }
    }

}
