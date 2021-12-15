package com.shake_match.alchomist.ingredient;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Cocktail> cocktails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public String getType() {
        return type;
    }

    public boolean isAlcohol() {
        return isAlcohol;
    }

    public String getMeasure() {
        return measure;
    }

    public void update(IngredientUpdateRequest request) {
        this.name = request.getIngredientName();
        this.cocktails = request.getCocktails();
    }

    public void addCocktail(Cocktail cocktail) {
        this.cocktails.add(cocktail);
    }

}
