package com.automaatio.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URL;

public class ElectricityPriceConnector {

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

    public String getElPrice(){
        return String.format("%.2f", getElPriceNow());
    }
}
