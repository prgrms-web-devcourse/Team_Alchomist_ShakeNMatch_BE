package com.shake_match.alchomist.theme;

import com.shake_match.alchomist.cocktail.Cocktail;
import com.shake_match.alchomist.global.BaseEntity;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "themes")
public class Theme extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "main_category")
    String mainCategory;

    @Column(name = "sub_category")
    String subCategory;

//    @OneToMany //TODO - many To many로 바뀔 수 있음, 검색 시 테마가 칵테일을 알아야할까
//    public List<Cocktail> cocktails = new ArrayList<>();
}
