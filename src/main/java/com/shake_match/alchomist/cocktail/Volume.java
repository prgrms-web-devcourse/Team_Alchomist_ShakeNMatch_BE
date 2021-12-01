package com.shake_match.alchomist.cocktail;

import com.shake_match.alchomist.ingredient.Ingredient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "volume")
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private int amount;

    @OneToOne
    private Ingredient ingredient;
}
