package com.bookhive.ufcg.bookhive.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class RecommendationService {

    private final String API_KEY = "AIzaSyA7g6717GETwvZt3fp5bQ0QFo-8f8Nkl9Y";
    private final String ENDPOINT_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro-latest:generateContent?key=" + API_KEY;

    public String generateContent(String genre, String title) {
        try {
            // Montar a requisição JSON
            String userInput = String.format("gostaria de baseado nesse livro %s, me indique outro livro, mas com o genero de %s. a resposta deve ser somente o titulo do livro indicado e o autor do livro, separado por hífen. E conter apenas um livro", title, genre);
            JsonObject requestJson = new JsonObject();
            JsonObject userPart = new JsonObject();
            userPart.addProperty("text", userInput);


            JsonObject userContent = new JsonObject();
            userContent.addProperty("role", "user");
            userContent.add("parts", userPart);

            requestJson.add("contents", userContent);

            // Configurações de geração
            JsonObject generationConfig = new JsonObject();
            generationConfig.addProperty("temperature", 1);
            generationConfig.addProperty("topK", 0);
            generationConfig.addProperty("topP", 0.95);
            generationConfig.addProperty("maxOutputTokens", 8192);
            generationConfig.addProperty("stopSequences", "");

            requestJson.add("generationConfig", generationConfig);

            // Configurações de segurança
            JsonObject safetySettings = new JsonObject();
            safetySettings.addProperty("category", "HARM_CATEGORY_HARASSMENT");
            safetySettings.addProperty("threshold", "BLOCK_MEDIUM_AND_ABOVE");
            requestJson.add("safetySettings", safetySettings);

            // Fazer a conexão HTTP
            URL url = new URL(ENDPOINT_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Enviar a requisição
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                System.out.println("enviou req");
                wr.write(requestJson.toString().getBytes());
            }

            // Ler a resposta da API
            StringBuilder response = new StringBuilder();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println("Raw JSON Response: " + response.toString());
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // Parsear o JSON de resposta
            return parseResponse(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao gerar conteúdo: " + e.getMessage();
        }
    }

    private String parseResponse(String jsonResponse) {
        StringBuilder result = new StringBuilder();
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonArray candidatesArray = jsonObject.getAsJsonArray("candidates");
            for (JsonElement candidateElement : candidatesArray) {
                JsonObject candidateObject = candidateElement.getAsJsonObject();
                JsonObject contentObject = candidateObject.getAsJsonObject("content");
                JsonArray partsArray = contentObject.getAsJsonArray("parts");
                for (JsonElement partElement : partsArray) {
                    JsonObject partObject = partElement.getAsJsonObject();
                    String text = partObject.get("text").getAsString();
                    result.append(text).append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao processar a resposta: " + e.getMessage();
        }
        return result.toString();
    }
}
