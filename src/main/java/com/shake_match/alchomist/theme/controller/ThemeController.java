package com.shake_match.alchomist.theme.controller;

import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.theme.dto.ThemeDto;
import com.shake_match.alchomist.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theme")
public class ThemeController {

    private final ThemeService service;

    @PostMapping
    public ApiResponse<ThemeDto> createTheme(@RequestBody ThemeDto dto) throws Exception{
        return ApiResponse.ok(service.createTheme(dto));
    }

    @GetMapping
    public ApiResponse<List<ThemeDto>> searchAll(){
        return ApiResponse.ok(service.findAll());
    }

}
