package com.darkcode.spring.app.Services;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// @Service  ESTA COMENTADO PORQUE HAY QUE GENERAR LA API KEY Y HACER LA CONEXION
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

    public String generarPregunta(String categoria) throws Exception {
        String prompt = "Genera una pregunta de trivia para la categoría " + categoria +
                        ", incluyendo 4 opciones (A, B, C, D) y marca cuál es la correcta.";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = "{"
                + "\"model\": \"text-davinci-003\","
                + "\"prompt\": \"" + prompt + "\","
                + "\"max_tokens\": 150,"
                + "\"temperature\": 0.7"
                + "}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                String.class
        );

        JsonNode jsonResponse = objectMapper.readTree(response.getBody());
        return jsonResponse.get("choices").get(0).get("text").asText().trim();
    }
}
