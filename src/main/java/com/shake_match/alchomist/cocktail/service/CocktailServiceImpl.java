package com.shake_match.alchomist.cocktail.service;

import com.shake_match.alchomist.cocktail.convertor.CocktailConvertor;
import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository repository;
    private final CocktailConvertor convertor;

    @Override
    @Transactional(readOnly = true)
    public CocktailDetailResponse searchDetail(Long id) throws Exception{
        Optional<Cocktail> cocktail = repository.findById(id);
        if(cocktail.isEmpty()){
            throw new EntityNotFoundException();
        }
        return convertor.toCocktailDetail(cocktail.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchResponse> searchByTheme(String mainCategory, String subCategory) {
        List<Cocktail> cocktailByTheme = repository.findByTheme(mainCategory, subCategory);

        if(cocktailByTheme.isEmpty()){
            throw new EntityNotFoundException();
        }

        List<SearchResponse> responses = new ArrayList<>();
        for(Cocktail cocktail : cocktailByTheme){
            responses.add(convertor.toSearchByTheme(cocktail));
        }
        return responses;
    }

    @Override
    public CocktailDetailResponse searchByName(String name) {
        Optional<Cocktail> cocktail = repository.findByName(name);
        if(cocktail.isEmpty()){
            throw new EntityNotFoundException();
        }
        return convertor.toCocktailDetail(cocktail.get());
    }

}
