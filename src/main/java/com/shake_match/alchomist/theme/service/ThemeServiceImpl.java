package com.shake_match.alchomist.theme.service;

import com.shake_match.alchomist.theme.Theme;
import com.shake_match.alchomist.theme.repository.ThemeRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService{

    private final ThemeRepository repository;

    @Override
    public Theme createTheme(Theme theme) throws Exception{
        if(repository.findByTheme(theme.getMainCategory(), theme.getSubCategory()).isPresent()){
            throw new DuplicateRequestException();
        }
        return repository.save(theme);
    }

    @Override
    public Theme findTheme(Theme theme) throws Exception{
        Optional<Theme> themes = repository.findByTheme(theme.getMainCategory(), theme.getSubCategory());
        if(themes.isEmpty()){
            throw new EntityNotFoundException();
        }
        return themes.get();
    }
}
