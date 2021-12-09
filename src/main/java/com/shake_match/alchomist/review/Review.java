package com.shake_match.alchomist.review;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import com.shake_match.alchomist.cocktail.domain.Cocktail;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "reviews")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    int rating;

    @Column
    String description;

    @Column
    String url;

    @ManyToOne
    Users users;

    @ManyToOne
    Cocktail cocktails;

    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Users getUsers() {
        return users;
    }

    public Cocktail getCocktails() {
        return cocktails;
    }
}
