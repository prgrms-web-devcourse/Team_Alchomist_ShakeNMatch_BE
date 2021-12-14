package com.shake_match.alchomist.theme.service;

import com.shake_match.alchomist.theme.convertor.ThemeConvertor;
import com.shake_match.alchomist.theme.domain.Theme;
import com.shake_match.alchomist.theme.dto.ThemeDto;
import com.shake_match.alchomist.theme.repository.ThemeRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService{

    private final ThemeRepository repository;
    private final ThemeConvertor convertor;

    @Override
    @Transactional
    public ThemeDto createTheme(ThemeDto themeDto) throws Exception{
        if(repository.findByTheme(themeDto.getMainCategory(), themeDto.getSubCategory()).isPresent()){
            throw new DuplicateRequestException();
        }
        return convertor.toDto(repository.save(new Theme(themeDto.getMainCategory(), themeDto.getSubCategory())));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ThemeDto> findAll(){
        List<ThemeDto> themeDtos = new ArrayList<>();
        for (Theme theme : repository.findAll()) {
            themeDtos.add(convertor.toDto(theme));
        }
        return themeDtos;
    }
}
