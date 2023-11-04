package com.automaatio.model;

import com.automaatio.controller.MainPageController;
import com.automaatio.utils.ElectricityPriceConnector;

import java.time.LocalDateTime;


public class Moottori extends Thread{
    private ElectricityPriceConnector elPrice;
    private boolean running = true;
    private MainPageController mainController;
    private double elPriceNow;
    private final int INTERVAL = 1000;

    public Moottori(MainPageController main){
        mainController = main;
        elPrice = new ElectricityPriceConnector();
    }

    public void run(){
        System.out.println("Moottori käynnistyi!");
        elPriceNow = elPrice.getElPriceNow();
        mainController.setElPriceInUI(elPrice.getElPriceNow());

        while(running){
            LocalDateTime currentTime = LocalDateTime.now();
            if (currentTime.getMinute() == 1 && currentTime.getSecond() == 0) {
                elPriceNow = elPrice.getElPriceNow();
            }

            mainController.setElPriceInUI(elPriceNow);
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void stopRunning(){
        running = false;
    }

}
