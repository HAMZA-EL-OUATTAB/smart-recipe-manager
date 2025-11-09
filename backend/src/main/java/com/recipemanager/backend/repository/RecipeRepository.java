package com.recipemanager.backend.repository;

import com.recipemanager.backend.model.Recipe;
import com.recipemanager.backend.model.DifficultyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByUserId(Long userId, Pageable pageable);

    List<Recipe> findByCategory(String category);

    List<Recipe> findByTitleContainingIgnoreCase(String title);

    List<Recipe> findByUserId(Long userId);

    long countByUserId(Long userId);

    List<Recipe> findByDifficulty(DifficultyLevel difficulty);

    List<Recipe> findByIsPublic(Boolean isPublic);
}