package com.converter.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyFilter {
    private JsonObject ratesJson;
    private Map<String, Double> filteredCurrencies;

    public CurrencyFilter(JsonObject ratesJson) {
        this.ratesJson = ratesJson;
        this.filteredCurrencies = new HashMap<>();
    }

    public void displayAvailableCurrencies() {
        JsonObject rates = ratesJson.getAsJsonObject("conversion_rates");
        rates.entrySet().forEach(entry -> System.out.println(entry.getKey()));
    }

    public void filterCurrencies(List<String> currencies) throws IllegalArgumentException {
        JsonObject rates = ratesJson.getAsJsonObject("conversion_rates");

        // Check if all currencies are valid
        boolean allValid = currencies.stream().allMatch(rates::has);

        if (!allValid) {
            throw new IllegalArgumentException("All currencies must be valid. Please check your input.");
        }

        // Proceed to filter currencies if all are valid
        filteredCurrencies = currencies.stream()
                .collect(Collectors.toMap(currency -> currency, currency -> rates.get(currency).getAsDouble()));

        System.out.println("Filtered Currencies:");
        filteredCurrencies.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void saveFilteredResults(String filename) throws IOException {
        // Use GsonBuilder to enable pretty printing
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject resultJson = new JsonObject();

        // Create a new JSON object for rates
        JsonObject ratesObject = new JsonObject();

        // Add filtered currencies to the rates object
        filteredCurrencies.forEach(ratesObject::addProperty);

        // Add the rates object and additional fields to the result JSON
        resultJson.add("rates", ratesObject);
        resultJson.addProperty("result", "success");
        resultJson.addProperty("base", "USD");

        // Write the result to a JSON file
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(resultJson, writer);
        }
    }
}
