package com.shake_match.alchomist.theme.service;

import com.shake_match.alchomist.theme.dto.ThemeDto;

import java.util.List;

public interface ThemeService {

    ThemeDto createTheme(ThemeDto themeDto) throws Exception;
    List<ThemeDto> findAll();
}
