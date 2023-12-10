package com.automaatio.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URL;

/**
 * The ElectricityPriceConnector class provides methods for fetching the current electricity
 * price from an external API and comparing it to the price limit set by the user.
 *
 * @author Mikko Hänninen
 * @version 1.0
 */

public class ElectricityPriceConnector {

    /**
     * Fetches the current electricity price from the Spot-Hinta API and compares
     * it to the price passed as parameter
     *
     * @param price The price to be compared
     * @return True if the given price is lower than the current electricity price
     * fetched from the API, otherwise false
     */
    public boolean isElPriceOver(int price){
        return price < getElPriceNow();
    }

    private double getElPriceNow(){
        double currentPrice = Double.MAX_VALUE;
        try{
            URI uri = java.net.URI.create("https://api.spot-hinta.fi/JustNow");
            URL url = uri.toURL();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(url);

            currentPrice = node.get("PriceWithTax").asDouble()*100;
            System.out.println();
            System.out.println("Hinta veroineen nyt: " + currentPrice + "snt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPrice;
    }

    /**
     * Fetches the current electricity price from the Spot-Hinta API and returns
     * it as a String rounded to 2 decimals
     *
     * @return The current electricity price as a String
     */
    public String getElPrice(){
        return String.format("%.2f", getElPriceNow());
    }
}
