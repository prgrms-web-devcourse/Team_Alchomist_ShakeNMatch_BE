package com.shake_match.alchomist.theme.dto;

import lombok.Getter;

@Getter
public class ThemeDto {
    private String mainCategory;
    private String subCategory;

    public ThemeDto(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
