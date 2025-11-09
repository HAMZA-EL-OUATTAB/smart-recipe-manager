package com.recipemanager.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recipes", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_category", columnList = "category"),
        @Index(name = "idx_difficulty", columnList = "difficulty")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ingredients;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String instructions;

    @Column(length = 50)
    private String category;

    @Column(name = "prep_time")
    private Integer prepTime;

    @Column(name = "cook_time")
    private Integer cookTime;

    @Column(name = "total_time")
    private Integer totalTime;

    private Integer servings;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private DifficultyLevel difficulty = DifficultyLevel.MEDIUM;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_public")
    private Boolean isPublic = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionInfo nutritionInfo;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void calculateTotalTime() {
        if (prepTime != null && cookTime != null) {
            this.totalTime = prepTime + cookTime;
        }
    }
}