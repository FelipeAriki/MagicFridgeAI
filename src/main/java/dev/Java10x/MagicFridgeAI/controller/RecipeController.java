package dev.Java10x.MagicFridgeAI.controller;

import dev.Java10x.MagicFridgeAI.DTOs.FoodItemDTO;
import dev.Java10x.MagicFridgeAI.service.ChatGptService;
import dev.Java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final FoodItemService foodItemService;
    private final ChatGptService chatGptService;

    RecipeController(FoodItemService foodItemService,ChatGptService chatGptService){
        this.foodItemService = foodItemService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> createRecipe(){
        List<FoodItemDTO> foodItemDTOS = this.foodItemService.getFoodItems();
        return this.chatGptService.generateRecipe(foodItemDTOS)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
