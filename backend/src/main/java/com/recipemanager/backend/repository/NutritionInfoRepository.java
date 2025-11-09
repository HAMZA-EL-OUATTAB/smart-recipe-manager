package com.recipemanager.backend.repository;

import com.recipemanager.backend.model.NutritionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutritionInfoRepository extends JpaRepository<NutritionInfo, Long> {

    Optional<NutritionInfo> findByRecipeId(Long recipeId);

    void deleteByRecipeId(Long recipeId);

    boolean existsByRecipeId(Long recipeId);
}