package com.shake_match.alchomist.users.repository;

import com.shake_match.alchomist.users.UsersIngredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserIngredientRepository extends JpaRepository<UsersIngredient, Long> {

    @Modifying
    @Query("delete from UsersIngredient ui where ui.user.id = :userId"
        + " and ui.ingredient.id in :igs")
    void deleteUsersIdAndIngredientIds(@Param("userId") Long userId, @Param("igs") List<Long> igs);

    @Modifying
    @Query("delete from UsersIngredient ui where ui.user.id = :userId")
    void deleteUsersIdAndIngredient(@Param("userId") Long id);
}
