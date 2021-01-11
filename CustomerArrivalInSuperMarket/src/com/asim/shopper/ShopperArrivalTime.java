package com.asim.shopper;

import com.asim.ShopFloor;
import com.asim.shopper.Shopper;

import java.util.TimerTask;

public class ShopperArrivalTime extends TimerTask {

ShopFloor shopFloor = new ShopFloor();

    @Override
    public void run() {
        ArrivalRate();
    }

    private void ArrivalRate(){
        try {
            //Send new Shopper to ShopFloor // First shopper has no delay //
            shopFloor.AddNewShopper(new Shopper());

            Thread.sleep(6000); // wait 6 seconds after sending new shopper to shopfloor

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
