package com.shake_match.alchomist.cocktail.convertor;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;
import com.shake_match.alchomist.cocktail.dto.CreateCocktailRequest;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;
import org.springframework.stereotype.Component;

@Component
public class CocktailConvertor {

    public CocktailDetailResponse toCocktailDetail(Cocktail cocktail){
        return new CocktailDetailResponse(cocktail.getId(),
                cocktail.getName(),
                cocktail.getVolumes(),
                cocktail.getThemes(),
                cocktail.getReviews(),
                cocktail.getRecipe(),
                cocktail.getImageUrl(),
                cocktail.getYoutubeLink(),
                cocktail.getLikes(),
                cocktail.getTotalRating()
        );
    }

    public SearchResponse toSearchByTheme(Cocktail cocktail){
        return new SearchResponse(cocktail.getId(),
                cocktail.getName(),
                cocktail.getImageUrl()
        );
    }

    public Cocktail toCocktailEntity(CreateCocktailRequest createCocktailRequest) {
        return new Cocktail(createCocktailRequest.getName(),
                createCocktailRequest.getRecipe(),
                createCocktailRequest.getImageUrl(),
                createCocktailRequest.getYoutubeLink()
        );
    }
}
