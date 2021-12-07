package com.shake_match.alchomist.cocktail.domain;

import com.shake_match.alchomist.ingredient.Ingredient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Entity(name = "volume")
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private int amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;
}
