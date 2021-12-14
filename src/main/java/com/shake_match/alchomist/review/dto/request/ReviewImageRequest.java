package com.shake_match.alchomist.review.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewImageRequest {

    private Users user;
    private Cocktail cocktail;
    private String imageUrl;
    private String description;
    private int rating;
}
