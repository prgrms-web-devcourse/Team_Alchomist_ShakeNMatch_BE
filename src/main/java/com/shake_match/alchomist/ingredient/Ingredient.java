package com.shake_match.alchomist.ingredient;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ingredients")
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @OneToMany(fetch = FetchType.EAGER)
    List<Cocktail> cocktails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }
}
