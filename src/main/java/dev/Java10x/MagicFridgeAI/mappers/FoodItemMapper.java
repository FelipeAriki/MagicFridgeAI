package dev.Java10x.MagicFridgeAI.mappers;

import dev.Java10x.MagicFridgeAI.DTOs.FoodItemDTO;
import dev.Java10x.MagicFridgeAI.model.FoodItem;

public class FoodItemMapper {

    public FoodItem mapToEntity(FoodItemDTO foodItemDTO){
        FoodItem foodItem = new FoodItem();
        foodItem.setId(foodItemDTO.getId());
        foodItem.setNome(foodItemDTO.getNome());
        foodItem.setCategoria(foodItemDTO.getCategoria());
        foodItem.setQuantidade(foodItemDTO.getQuantidade());
        foodItem.setValidade(foodItemDTO.getValidade());

        return foodItem;
    }

    public FoodItemDTO mapToDto(FoodItem foodItem){
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setNome(foodItem.getNome());
        foodItemDTO.setCategoria(foodItem.getCategoria());
        foodItemDTO.setQuantidade(foodItem.getQuantidade());
        foodItemDTO.setValidade(foodItem.getValidade());

        return foodItemDTO;
    }
}
