package com.asim.shopper;

import com.asim.controllers.ShopFloorController;
import com.asim.shopper.ShopperModel;

import java.util.TimerTask;

public class ShopperSpawnRate extends TimerTask {

    private static long arrivalRate;
    private static int shoppersEntered = 0;

    @Override
    public void run() {
        ArrivalRate();
    }

    private void ArrivalRate(){
        try {
            //Stores number of shoppers entered
            shoppersEntered++;

            //Send new Shopper to ShopFloor // First shopper has no delay //
            new ShopFloorController().RegisterObserver(new ShopperModel());

            System.out.println("<< Shoppers arrived "+ shoppersEntered +" >> " );

            Thread.sleep(arrivalRate);

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
