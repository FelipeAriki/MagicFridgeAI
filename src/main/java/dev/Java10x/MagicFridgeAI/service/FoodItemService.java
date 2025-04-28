package dev.Java10x.MagicFridgeAI.service;

import dev.Java10x.MagicFridgeAI.DTOs.FoodItemDTO;
import dev.Java10x.MagicFridgeAI.mappers.FoodItemMapper;
import dev.Java10x.MagicFridgeAI.model.FoodItem;
import dev.Java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;

    FoodItemService(FoodItemRepository foodItemRepository, FoodItemMapper foodItemMapper){
        this.foodItemRepository = foodItemRepository;
        this.foodItemMapper = foodItemMapper;
    }

    public List<FoodItemDTO> getFoodItems(){
        List<FoodItem> foodItems = this.foodItemRepository.findAll();
        return foodItems.stream()
                .map(this.foodItemMapper::mapToDto)
                .collect(Collectors.toList());
    }
    public FoodItemDTO getFoodItem(Long id){
        Optional<FoodItem> foodItem = this.foodItemRepository.findById(id);
        return foodItem
                .map(this.foodItemMapper::mapToDto)
                .orElse(null);

    }

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO){
        FoodItem foodItemEntity = foodItemMapper.mapToEntity(foodItemDTO);
        this.foodItemRepository.save(foodItemEntity);

        return this.foodItemMapper.mapToDto(foodItemEntity);
    }

    public FoodItemDTO updateFoodItem(Long id, FoodItemDTO foodItemDTO) {
        Optional<FoodItem> foodItemExist = this.foodItemRepository.findById(id);
        if (foodItemExist.isPresent()) {
            FoodItem updatedFoodItem = this.foodItemMapper.mapToEntity(foodItemDTO);
            updatedFoodItem.setId(id);
            FoodItem savedFoodItem = this.foodItemRepository.save(updatedFoodItem);
            return this.foodItemMapper.mapToDto(savedFoodItem);
        }
        return null;
    }

    public void deleteFoodItem(Long id){
        this.foodItemRepository.deleteById(id);
    }
}
