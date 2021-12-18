package com.shake_match.alchomist.cocktail.service;

import com.shake_match.alchomist.amazon.service.S3Service;
import com.shake_match.alchomist.cocktail.convertor.CocktailConvertor;
import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.CocktailIngredient;
import com.shake_match.alchomist.cocktail.domain.Volume;
import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;
import com.shake_match.alchomist.cocktail.dto.CreateCocktailRequest;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;
import com.shake_match.alchomist.cocktail.repository.CocktailIngredientRepository;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import com.shake_match.alchomist.review.repository.ReviewRepository;
import com.shake_match.alchomist.review.service.ReviewService;
import com.shake_match.alchomist.theme.domain.Theme;
import com.shake_match.alchomist.theme.repository.ThemeRepository;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository repository;
    private final CocktailConvertor convertor;
    private final IngredientRepository ingredientRepository;
    private final ThemeRepository themeRepository;
    private final ReviewRepository reviewRepository;
    private final S3Service s3Service;
    private final CocktailIngredientRepository cocktailIngredientRepository;


    @Override
    @Transactional(readOnly = true)
    public CocktailDetailResponse searchDetail(Long id) throws Exception {
        Optional<Cocktail> cocktail = repository.findById(id);
        if (cocktail.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return convertor.toCocktailDetail(cocktail.get(), reviewRepository.findByCocktailId(cocktail.get().getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchResponse> searchByTheme(String mainCategory, String subCategory) {
        List<Cocktail> cocktailByTheme = repository.findByTheme(mainCategory, subCategory);

        if (cocktailByTheme.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<SearchResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktailByTheme) {
            responses.add(convertor.toSearch(cocktail));
        }
        return responses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchResponse> searchByName(String name) {
        Optional<Cocktail> cocktail = repository.findByName(name);
        if (cocktail.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<SearchResponse> responses = new ArrayList<>();
        responses.add(convertor.toSearch(cocktail.get()));
        return responses;
    }

    @Override
    @Transactional
    public String createCocktail(CreateCocktailRequest createCocktailRequest) throws Exception {
        if (repository.findByName(createCocktailRequest.getName()).isPresent()) {
            throw new DuplicateRequestException();
        }

        List<Theme> themes = new ArrayList<>();
        for (String tmp : createCocktailRequest.getTheme()) {
            String[] themeInput = tmp.split(":");
            themes.add(themeRepository.findByTheme(themeInput[0], themeInput[1]).get());
        }

        List<Volume> volumes = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        for (String tmp : createCocktailRequest.getIngredient()) {
            String[] ingredientInput = tmp.split(":");
            Ingredient ingredient = ingredientRepository.findByName(ingredientInput[0]).get();
            ingredients.add(ingredient);
            volumes.add(new Volume(Double.parseDouble(ingredientInput[1]), ingredient));
        }

        Cocktail cocktail = repository.save(
            convertor.toCocktail(createCocktailRequest, themes, volumes));
        return "success";
    }

    @Override
    @Transactional
    public String deleteCocktail(String name) throws Exception {
        Optional<Cocktail> cocktail = repository.findByName(name);
        if (cocktail.isEmpty()) {
            throw new EntityNotFoundException();
        }
        s3Service.delete(cocktail.get().getType());
        repository.deleteById(cocktail.get().getId());
        return "success";
    }

    @Override
    @Transactional
    public void cocktailandIngredient(Long cocktail_id, Long ingredient_id) {
        Cocktail cocktail = repository.findById(cocktail_id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        Ingredient ingredient = ingredientRepository.findById(ingredient_id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_INGREDIENT));
        CocktailIngredient cocktailIngredient = new CocktailIngredient(cocktail, ingredient);
        cocktailIngredientRepository.save(cocktailIngredient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchResponse> searchAll() throws Exception {
        List<Cocktail> cocktails = repository.findAll();
        List<SearchResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktails) {
            responses.add(convertor.toSearch(cocktail));
        }
        return responses;
    }
}
