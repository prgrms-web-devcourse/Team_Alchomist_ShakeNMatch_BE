package com.shake_match.alchomist.cocktail.dto;

import lombok.Getter;

@Getter
public class VolumeDto {

    private Long ingredientId;
    private String name;
    private Double amount;
//    private String measure;

    public VolumeDto(Long ingredientId, String name, Double amount) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.amount = amount;
//        this.measure = measure;
    }
}
