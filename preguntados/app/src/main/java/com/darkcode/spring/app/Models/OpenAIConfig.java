package com.darkcode.spring.app.Models;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAIConfig {

    @Value("${openai.api.key}")
    private String apiKey;

    // @Value("${openai.api.url}")  ESTA COMENTADO PORQUE HAY QUE GENERAR LA API KEY Y HACER LA CONEXION
    // private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

   // public String getApiUrl() {
   //     return apiUrl;
   // }
}