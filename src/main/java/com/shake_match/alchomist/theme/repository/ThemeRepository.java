package com.shake_match.alchomist.theme.repository;

import com.shake_match.alchomist.theme.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("SELECT t FROM themes t WHERE t.mainCategory=:mainCategory AND t.subCategory=:subCategory")
    Optional<Theme> findByTheme(@Param("mainCategory") String mainCategory, @Param("subCategory") String subCategory);
}
