package com.shake_match.alchomist.cocktail.dto;

import lombok.Getter;

@Getter
public class VolumeDto {

    private Long id;
    private String name;
    private Double amount;
    private String type;
    private String measure;

    public VolumeDto(Long id, String name, Double amount, String type, String measure) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.measure = measure;
    }
}
