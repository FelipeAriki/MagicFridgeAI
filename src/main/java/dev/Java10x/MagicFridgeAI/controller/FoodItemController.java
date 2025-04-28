package dev.Java10x.MagicFridgeAI.controller;

import dev.Java10x.MagicFridgeAI.DTOs.FoodItemDTO;
import dev.Java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    FoodItemController(FoodItemService foodItemService){
        this.foodItemService = foodItemService;
    }

    @GetMapping("/get-items")
    public ResponseEntity<?> GetFoodItems(){
        List<FoodItemDTO> foodItems = this.foodItemService.getFoodItems();
        return ResponseEntity.ok(foodItems);
    }
    @GetMapping("/get-item/{id}")
    public ResponseEntity<?> GetFoodItem(@PathVariable Long id){
        FoodItemDTO foodItem = this.foodItemService.getFoodItem(id);
        return ResponseEntity.ok(foodItem);
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateFoodItem(@RequestBody FoodItemDTO foodItem){
        FoodItemDTO newItem = this.foodItemService.createFoodItem(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Item" + newItem.getNome() + "criado com sucesso!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateFoodItem(@PathVariable Long id, @RequestBody FoodItemDTO updatedFoodItemDTO) {
        FoodItemDTO foodItem = this.foodItemService.updateFoodItem(id, updatedFoodItemDTO);
        if (foodItem != null) {
            return ResponseEntity.ok(foodItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item com o id: " + id + " nao existe nos nossos registros");
        }

        /*return this.foodItemService.getFoodItem(id)
                .map(itemExistente -> {
                    updatedFoodItemDTO.setId(itemExistente.getId());
                    FoodItemDTO updated = this.foodItemService.updateFoodItem(id, updatedFoodItemDTO);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
         OBS: Nesse caso deveria trocar na service para retornar um Optional<FoodItemDTO>
         */
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteFoodItem(@PathVariable Long id){
        if(this.foodItemService.getFoodItem(id) != null){
            this.foodItemService.deleteFoodItem(id);
            return ResponseEntity.ok("Item com o ID: " + id + "excluído com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Item não encontrado!");
    }
}
