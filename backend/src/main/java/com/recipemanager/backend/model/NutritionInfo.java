package com.recipemanager.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutrition_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    private Integer calories;

    @Column(name = "protein_grams")
    private Double protein;

    @Column(name = "carbs_grams")
    private Double carbs;

    @Column(name = "fats_grams")
    private Double fats;
}