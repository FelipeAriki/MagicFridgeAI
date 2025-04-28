package dev.Java10x.MagicFridgeAI.repository;

import dev.Java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
