package com.shake_match.alchomist.cocktail.domain;

import com.shake_match.alchomist.ingredient.Ingredient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Entity(name = "volume")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    public Volume(Double amount, Ingredient ingredient) {
        this.amount = amount;
        this.ingredient = ingredient;
    }
}
