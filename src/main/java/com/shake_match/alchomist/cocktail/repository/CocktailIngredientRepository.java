package com.shake_match.alchomist.cocktail.repository;

import com.shake_match.alchomist.cocktail.domain.CocktailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocktailIngredientRepository extends JpaRepository<CocktailIngredient, Long> {

}
