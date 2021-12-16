package com.shake_match.alchomist.cocktail.domain;

import com.shake_match.alchomist.ingredient.Ingredient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "cocktail_ingredient")
@Entity
public class CocktailIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public CocktailIngredient(Cocktail cocktail,
                              Ingredient ingredient) {
        this.cocktail = cocktail;
        this.ingredient = ingredient;
    }
}
