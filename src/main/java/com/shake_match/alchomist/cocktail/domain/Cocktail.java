package com.shake_match.alchomist.cocktail.domain;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.theme.domain.Theme;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity(name = "cocktails")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cocktail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    List<Volume> volumes = new ArrayList<>();

    @ManyToMany
    List<Theme> themes = new ArrayList<>();

    @OneToMany
    List<Review> reviews = new ArrayList<>();

    @Column
    String recipe; //TODO: 프론트와 recipe의 상세 확인해보기

    @Column
    String type;

    @Column
    int likes;

    @Column
    float totalRating;

    public Cocktail(String name, String recipe, String type, List<Theme> themes, List<Volume> volumes) {
        this.name = name;
        this.recipe = recipe;
        this.type = type;
        this.themes = themes;
        this.volumes = volumes;
        this.likes = 0;
        this.totalRating = 0;
    }

    public void addThemes(Theme theme) {
        this.themes.add(theme);
    }

    public void addReviews(Review review){
        this.reviews.add(review);
    }

    public void addLikes(boolean bool){
        if(bool){
            likes++;
        }
        else{
            likes--;
        }
    }

}
