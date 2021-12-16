package com.shake_match.alchomist.ingredient;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.CocktailIngredient;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import lombok.*;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ingredients")
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "is_alcohol")
    private boolean isAlcohol;

    @Column(name = "measure")
    private String measure;

    @OneToMany(mappedBy = "ingredient")
    private List<CocktailIngredient> cocktailIngredients = new ArrayList<>();

    public void update(IngredientUpdateRequest request) {
        this.name = request.getIngredientName();
        this.type = request.getType();
        this.isAlcohol = request.isAlcohol();
        this.measure = request.getMeasure();
    }
}
