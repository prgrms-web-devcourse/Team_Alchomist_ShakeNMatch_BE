package com.shake_match.alchomist.review;

import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.review.dto.request.ReviewUpdateRequest;
import com.shake_match.alchomist.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.shake_match.alchomist.cocktail.domain.Cocktail;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "user_id")
    Long userId;

    @Column(name = "cocktail_id")
    Long cocktailId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    Users users;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    Cocktail cocktails;

    public void update(ReviewUpdateRequest request) {
        this.rating = request.getRating();
        this.description = request.getDescription();
        this.url = request.getUrl();
    }
}
