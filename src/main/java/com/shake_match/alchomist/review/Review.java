package com.shake_match.alchomist.review;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.cocktail.Cocktail;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "reviews")
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
}
