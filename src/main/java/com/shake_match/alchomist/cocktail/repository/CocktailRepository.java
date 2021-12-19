package com.shake_match.alchomist.cocktail.repository;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.CocktailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

    Optional<Cocktail> findByName(String name);

    @Query("SELECT distinct c FROM cocktails c LEFT JOIN FETCH c.themes t WHERE t.mainCategory=:mainCategory AND t.subCategory=:subCategory")
    List<Cocktail> findByTheme(@Param("mainCategory") String mainCategory, @Param("subCategory") String subCategory);

    @Query("SELECT distinct ci FROM CocktailIngredient ci"
        + " JOIN FETCH ci.cocktail c"
        + " JOIN FETCH ci.ingredient i"
        + " WHERE ci.ingredient.id IN :ingredientIds")
    List<CocktailIngredient> findAllByIngredients(@Param("ingredientIds") List<Long> ingredientIds);
}
