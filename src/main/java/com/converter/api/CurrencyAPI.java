package com.converter.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyAPI {
    public JsonObject fetchRates(String apiUrl) throws IOException, InterruptedException {
        // Create HttpClient and HttpRequest
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        // Send request and receive response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch rates. Status code: " + response.statusCode());
        }

        // Parse JSON response using Gson
        JsonParser parser = new JsonParser();
        return parser.parse(response.body()).getAsJsonObject();
    }
}
