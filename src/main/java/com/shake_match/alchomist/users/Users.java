package com.shake_match.alchomist.users;

import com.shake_match.alchomist.cocktail.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.review.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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


}
