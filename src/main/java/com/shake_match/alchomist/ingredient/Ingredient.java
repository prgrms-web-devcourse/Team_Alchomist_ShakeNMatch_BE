package com.shake_match.alchomist.ingredient;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

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

    public void update(IngredientUpdateRequest request) {
        this.name = request.getIngredientName();
        this.cocktails = request.getCocktails();
    }
}
