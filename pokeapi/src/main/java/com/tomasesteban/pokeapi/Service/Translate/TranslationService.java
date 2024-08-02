package com.tomasesteban.pokeapi.Service.Translate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TranslationService {

    private final RestTemplate restTemplate;

    @Value("${deepl.api.url}")
    private String deeplApiUrl;

    @Value("${deepl.api.key}")
    private String deeplApiKey;

    public TranslationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String translate(String text, String targetLanguage) {
        String url = UriComponentsBuilder.fromHttpUrl(deeplApiUrl)
            .queryParam("auth_key", deeplApiKey)
            .queryParam("text", text)
            .queryParam("target_lang", targetLanguage)
            .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            // Procesa la respuesta para extraer el texto traducido
            // Aquí deberías ajustar el procesamiento basado en la estructura de la respuesta de DeepL
            return extractTranslation(response);
        } catch (Exception e) {
            e.printStackTrace();
            return text; // Devuelve el texto original si ocurre un error
        }
    }

    private String extractTranslation(String response) {
        // Implementa la lógica para extraer la traducción de la respuesta JSON
        // Por ejemplo, usando una biblioteca JSON como Jackson o Gson
        // Aquí hay un ejemplo con Jackson:
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            return rootNode.path("translations").get(0).path("text").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return response; // Devuelve la respuesta original si ocurre un error
        }
    }
}