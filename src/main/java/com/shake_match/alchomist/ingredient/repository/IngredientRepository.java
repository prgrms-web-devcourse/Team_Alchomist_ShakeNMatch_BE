package com.shake_match.alchomist.ingredient.repository;

import com.shake_match.alchomist.ingredient.Ingredient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);

}
