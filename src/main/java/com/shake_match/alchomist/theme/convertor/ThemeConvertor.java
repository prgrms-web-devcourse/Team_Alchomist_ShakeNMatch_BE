package com.shake_match.alchomist.theme.convertor;

import com.shake_match.alchomist.theme.domain.Theme;
import com.shake_match.alchomist.theme.dto.ThemeDto;
import org.springframework.stereotype.Component;

@Component
public class ThemeConvertor {
    public static ThemeDto toDto(Theme theme) {
        return new ThemeDto(theme.getMainCategory(), theme.getSubCategory());
    }
}
