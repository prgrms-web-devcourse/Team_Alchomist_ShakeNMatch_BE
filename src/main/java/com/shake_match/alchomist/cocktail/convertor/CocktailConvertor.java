package com.shake_match.alchomist.cocktail.convertor;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.Volume;
import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;

import com.shake_match.alchomist.cocktail.dto.CreateCocktailRequest;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;
import com.shake_match.alchomist.cocktail.dto.VolumeDto;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.converter.ReviewConverter;
import com.shake_match.alchomist.review.dto.ReviewDto;
import com.shake_match.alchomist.theme.convertor.ThemeConvertor;
import com.shake_match.alchomist.theme.domain.Theme;
import com.shake_match.alchomist.theme.dto.ThemeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CocktailConvertor {

    private final String bucket = "team15-image-bucket";
    private final String region = "ap-northeast-2";
    private final String s3Address = "https://" + bucket + ".s3." + region + ".amazonaws.com/";

    public CocktailDetailResponse toCocktailDetail(Cocktail cocktail, List<Review> reviews){
        List<VolumeDto> volumeDtos = new ArrayList<>();
        for(Volume volume: cocktail.getVolumes()){
            volumeDtos.add(toVolumeDto(volume));
        }

        List<ThemeDto> themeDtos = new ArrayList<>();
        for(Theme theme: cocktail.getThemes()){
            themeDtos.add(ThemeConvertor.toDto(theme));
        }

        float rating = 0;
        float count = 0;
        float totalRating = 0;
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for(Review review: reviews){
            count++;
            rating += (float) review.getRating();
            reviewDtos.add(ReviewConverter.toReviewDto(review));
        }
        if (count != 0){
            totalRating = rating / count;
        }

        return new CocktailDetailResponse(cocktail.getId(),
                cocktail.getName(),
                volumeDtos,
                themeDtos,
                reviewDtos,
                cocktail.getRecipe(),
                cocktail.getType(),
                cocktail.getLikes(),
                totalRating
        );
    }

    private VolumeDto toVolumeDto(Volume volume) {
        return new VolumeDto(volume.getIngredient().getId(),
                volume.getIngredient().getName(),
                volume.getAmount(),
                volume.getIngredient().getType(),
                volume.getIngredient().getMeasure()
                );
    }

    public SearchResponse toSearch(Cocktail cocktail){
        return new SearchResponse(cocktail.getId(),
                cocktail.getName(),
                cocktail.getType()
        );
    }

    public Cocktail toCocktail(CreateCocktailRequest createCocktailRequest, List<Theme> themes, List<Volume> volumes){
        return new Cocktail(
                createCocktailRequest.getName(),
                createCocktailRequest.getRecipe(),
                s3Address + createCocktailRequest.getType(),
                themes,
                volumes
        );
    }
}
