package com.darkcode.spring.app.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service 
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    @SuppressWarnings("unused")
    private final ObjectMapper objectMapper;

    public OpenAIService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

   public String generarPregunta(String categoria) {
    try {
        // Crear el prompt
        String prompt = "Genera una pregunta de trivia para la categoría " + categoria +
                ". Solo una de las opciones debe ser correcta, y el orden de la respuesta correcta debe ser aleatorio con una probabilidad equitativa del 25% de que esté en cualquiera de las 4 posiciones (A, B, C o D). Formato:\n" +
                "¿Cuál es la capital de Francia?\n" +
                "A) París\n" +
                "B) Madrid\n" +
                "C) Berlín\n" +
                "D) Roma\n" +
                "Respuesta correcta: [Letra de la opción correcta]" +
                "Responde solo en este formato.";

        // Configurar encabezados HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Crear el cuerpo de la solicitud como un mapa
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));
        requestBody.put("max_tokens", 150);
        requestBody.put("temperature", 0.7);

        // Convertir el mapa a JSON usando ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        // Crear la solicitud HTTP
        HttpEntity<String> request = new HttpEntity<>(jsonRequestBody, headers);

        // Hacer la solicitud POST
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl, // Asegúrate de que sea "https://api.openai.com/v1/chat/completions"
                HttpMethod.POST,
                request,
                String.class
        );

        // Parsear la respuesta JSON
        JsonNode jsonResponse = objectMapper.readTree(response.getBody());
        return jsonResponse.get("choices").get(0).get("message").get("content").asText().trim();

    } catch (Exception e) {
        e.printStackTrace();
        return "Error al generar la pregunta: " + e.getMessage();
    }
}

    
}
