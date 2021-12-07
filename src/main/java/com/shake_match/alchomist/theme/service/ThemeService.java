package com.shake_match.alchomist.theme.service;

import com.shake_match.alchomist.theme.Theme;

public interface ThemeService {

    public Theme createTheme(Theme theme) throws Exception;
    public Theme findTheme(Theme theme) throws Exception;
}
