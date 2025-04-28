package dev.Java10x.MagicFridgeAI.service;

import dev.Java10x.MagicFridgeAI.DTOs.FoodItemDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {
    private final WebClient webClient;
    private final String apiKey = System.getenv("API_KEY");

    ChatGptService(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItemDTO> foodItemDTOS) {
        String alimentos = foodItemDTOS
                .stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getNome(), item.getCategoria(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("\n"));

        String prompt = "Baseado no meu banco de dados faça uma receita com os seguintes itens:\n"+alimentos;


        Map<String, Object> requestBody = Map.of("model", "gpt-4.1", "input", prompt);
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> {
                    var outputs = (List<Map<String, Object>>) resp.get("output");
                    if (outputs != null && !outputs.isEmpty()) {
                        var content = (List<Map<String, Object>>) outputs.get(0).get("content");
                        if (content != null && !content.isEmpty()) {
                            return content.get(0).get("text").toString();
                        }
                    }
                    return "No recipe found.";
                });
    }
}
