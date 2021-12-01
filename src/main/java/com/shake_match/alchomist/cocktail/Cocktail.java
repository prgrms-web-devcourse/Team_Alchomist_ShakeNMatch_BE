package com.shake_match.alchomist.cocktail;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.theme.Theme;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "cocktails")
public class Cocktail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    List<Volume> volumes = new ArrayList<>();

    @OneToMany
    List<Theme> themes = new ArrayList<>();

    @OneToMany(mappedBy = "cocktails")
    List<Review> reviews = new ArrayList<>();

    @Column
    String recipe; //TODO: 프론트와 recipe의 상세 확인해보기

    @Column
    String imageUrl;

    @Column
    String youtubeLink;

    @Column
    int likes;

    @Column
    float totalRating;
}
