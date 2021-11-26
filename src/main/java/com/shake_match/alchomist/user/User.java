package com.shake_match.alchomist.user;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.Ingredient;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "users")
public class User extends BaseEntity {

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

    @OneToMany
    List<Ingredient> ingredient = new ArrayList<>();

    @Column(nullable = false)
    boolean gender;

    @Column(nullable = false)
    int age;

    @Column
    String mbti;

}
