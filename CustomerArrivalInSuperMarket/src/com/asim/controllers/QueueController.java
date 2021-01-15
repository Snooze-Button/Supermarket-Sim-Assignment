package com.asim.controllers;

import com.asim.interfaces.Observable;
import com.asim.interfaces.Observer;
import com.asim.shopper.ShopperModel;
import com.asim.shopper.ShopperView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

public class QueueController implements Observable {

    //List make it static only 1 instance
    static Queue<ShopperModel> queue = new LinkedList<ShopperModel>();

    private int checkOutTime;

    ShopperView shopperView = new ShopperView();
    Timer timer = new Timer(true);

    @Override
    public void RegisterObserver(ShopperModel shopperModel) {
        queue.add(shopperModel);//Why am I casting ?

      checkOutTime = shopperModel.getItemsDelay()/4;

      WaitforItemScan();

    }

    public void WaitforItemScan(){
        try {
            Thread.sleep(checkOutTime); // Customer will take 1/4th of the time (that took to fill their basket) to checkoyt
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        NotifyObservers();

    }

    @Override
    public void RemoveObserver(Observer observer) {
        queue.remove(observer);
    }

    @Override
    public void NotifyObservers() {

        //Tell shopper to start bagging the item
        queue.peek().Update(queue);
        shopperView.Update(queue);

    }



}
