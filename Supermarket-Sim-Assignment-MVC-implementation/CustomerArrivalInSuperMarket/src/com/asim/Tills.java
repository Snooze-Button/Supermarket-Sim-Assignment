package com.asim;

import com.asim.controllers.QueueController;
import com.asim.shopper.ShopperModel;

import java.util.ArrayList;

public class Tills {

    //Setter added to initialise max Queue Size and Tills
    private static int maxQueueSize;
    private static int maxTills;

    private static int increment;

    //Tills is a list of queues
    static ArrayList<QueueController> tills = new ArrayList<QueueController>();

     public void addShopperToQueue(ShopperModel shopperModel) {

        //Limit amount of till to maxTills constraint//
        if(tills.size() < maxTills) {
            for (int i = 0; i < maxTills; i++) {
                tills.add(new QueueController());
              //  System.out.println("Check if Loop is Executing");
            }
        }

        boolean previousTillHasSpace = (tills.indexOf(increment -1) >= 0) && tills.get(increment - 1).getQueue().size() <= maxQueueSize;

              if(tills.get(increment).getQueue().size() <= maxQueueSize){ // if current till has than 11 allow customer in the queue

                tills.get(increment).RegisterObserver(shopperModel);

            }else if(previousTillHasSpace){ // if check to see if previous till has space opened
                increment--;
                tills.get(increment).RegisterObserver(shopperModel);

            }else{                                                //open new till
                increment++;
                tills.get(increment).RegisterObserver(shopperModel);

        }
    }

    public void setMaxQueueSize(int maxQueueSize) {

        this.maxQueueSize = maxQueueSize;

    }

    public void setMaxTills(int maxTillSize){

        this.maxTills = maxTillSize;

    }



}
