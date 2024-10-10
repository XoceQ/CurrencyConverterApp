package com.converter;

import com.converter.api.CurrencyAPI;
import com.converter.filter.CurrencyFilter;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            // Fetch exchange rates
            String apiUrl = "https://v6.exchangerate-api.com/v6/f2e7116ed18df69a560686eb/latest/USD";
            CurrencyAPI currencyAPI = new CurrencyAPI();
            JsonObject ratesJson = currencyAPI.fetchRates(apiUrl);

            // Filter and display currencies
            CurrencyFilter filter = new CurrencyFilter(ratesJson);
            filter.displayAvailableCurrencies();

            Scanner scanner = new Scanner(System.in);
            List<String> selectedCurrencies;

            do {
                System.out.println("Enter the currencies you're interested in converting (comma-separated) EX: USD,COP,etc...: ");
                String input = scanner.nextLine();
                selectedCurrencies = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());

                try {
                    filter.filterCurrencies(selectedCurrencies);
                    break; // Exit the loop if filtering was successful
                } catch (IllegalArgumentException e) {
                    System.out.println("Input Error: " + e.getMessage());
                }
            } while (true); // Continue looping until valid input is received

            // Save to JSON file
            System.out.println("Saving results to 'filtered_rates.json'...");
            filter.saveFilteredResults("filtered_rates.json");
            System.out.println("Results saved successfully!");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}