package com.darkcode.spring.app.Services;
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
    private final ObjectMapper objectMapper;

    public OpenAIService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String generarPregunta(String categoria) {
        try {
            // Construir el prompt para el modelo
            String prompt = "Genera una pregunta de trivia para la categoría " + categoria +
                ", con exactamente 4 opciones (A, B, C, D), donde solamente una debe de ser la correcta y asegurando que cada opción sea única y clara.";
    
            // Configurar encabezados HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");
    
            // Formatear el cuerpo de la solicitud (usando `messages` para modelos de chat)
            String requestBody = "{"
                    + "\"model\": \"gpt-3.5-turbo\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}],"
                    + "\"max_tokens\": 150,"
                    + "\"temperature\": 0.7"
                    + "}";
    
            // Crear la solicitud HTTP
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
    
            // Usar la URL correcta para modelos de chat
            String chatApiUrl = apiUrl; // Asegúrate de que apiUrl = "https://api.openai.com/v1/chat/completions"
    
            ResponseEntity<String> response = restTemplate.exchange(
                    chatApiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );
    
            // Analizar la respuesta JSON
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            return jsonResponse.get("choices").get(0).get("message").get("content").asText().trim();
    
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al generar la pregunta: " + e.getMessage();
        }
    }
    
}
