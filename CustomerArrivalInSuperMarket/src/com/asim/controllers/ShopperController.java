package com.asim.controllers;

import com.asim.controllers.ShopFloorController;
import com.asim.shopper.ShopperModel;

import java.util.TimerTask;

public class ShopperController extends TimerTask {

    private static long arrivalRate;
    private static int shoppersEntered = 0;

    @Override
    public void run() {
        ArrivalRate();
    }

    private void ArrivalRate(){
        try {
            //Send new Shopper to ShopFloor // First shopper has no delay //
            new ShopFloorController().RegisterObserver(new ShopperModel());

            //Stores all shoppers entered
           shoppersEntered++;
            System.out.println("Shoppers arrived  " + shoppersEntered + "  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");

            Thread.sleep(arrivalRate); // 5 seconds intervals



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setArrivalRate(long shoppersPerMilliseconds) {

        arrivalRate = shoppersPerMilliseconds;


    }

    public int getShoppersEntered(){

        return shoppersEntered;

    }


}
