package com.shake_match.alchomist.theme.domain;

import com.shake_match.alchomist.global.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "themes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Theme extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "main_category")
    String mainCategory;

    @Column(name = "sub_category")
    String subCategory;

    public Theme(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
