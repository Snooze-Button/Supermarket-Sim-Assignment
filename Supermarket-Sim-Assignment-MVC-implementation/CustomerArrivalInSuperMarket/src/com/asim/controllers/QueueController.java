package com.asim.controllers;

import com.asim.interfaces.Observable;
import com.asim.interfaces.Observer;
import com.asim.shopper.ShopperModel;
import com.asim.View;

import java.util.*;

//Add Tills and queues inside tills

public class QueueController implements Observable {

    //Remove static to have multiple instances //
   private Queue<ShopperModel> queue = new LinkedList<ShopperModel>();

    private int checkOutTime;

    public Queue<ShopperModel> getQueue(){
        return queue;
    }

    View shopperView = new View();
    Timer timer = new Timer(true);

    @Override
     public void RegisterObserver(ShopperModel shopperModel) {

          queue.add(shopperModel);

      checkOutTime = shopperModel.getItemsDelay()/4;

        NotifyObservers();
    }

    @Override
     public void RemoveObserver(Observer observer) {
        queue.remove(observer);
    }

    @Override
     public void NotifyObservers() {

        //Waiting Items to be scanned
        try {
            Thread.sleep(checkOutTime); // Customer will take 1/4th of the time (that took to fill their basket) to checkoyt
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Tell shopper to start bagging the item
        queue.peek().Update(queue);

    }

}
