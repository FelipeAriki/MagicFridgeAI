package dev.Java10x.MagicFridgeAI.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private Long Id;
    private String nome;
    private String categoria;
    private int quantidade;
    private LocalDate validade;
}
