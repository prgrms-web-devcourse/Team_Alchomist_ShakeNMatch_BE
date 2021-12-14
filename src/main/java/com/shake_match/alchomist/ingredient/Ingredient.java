package com.shake_match.alchomist.ingredient;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import lombok.*;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ingredients")
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "is_alcohol")
    private boolean isAlcohol;

    @Column(name = "measure")
    private String measure;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Cocktail> cocktails = new ArrayList<>();

    public void update(IngredientUpdateRequest request) {
        this.name = request.getIngredientName();
        this.cocktails = request.getCocktails();
        this.type = request.getType();
        this.isAlcohol = request.isAlcohol();
        this.measure = request.getMeasure();
    }

    public void addCocktail(Cocktail cocktail){
        this.cocktails.add(cocktail);
    }
}
