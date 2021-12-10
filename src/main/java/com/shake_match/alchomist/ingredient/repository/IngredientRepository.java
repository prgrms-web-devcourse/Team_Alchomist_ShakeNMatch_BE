package com.shake_match.alchomist.ingredient.repository;

import com.shake_match.alchomist.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
