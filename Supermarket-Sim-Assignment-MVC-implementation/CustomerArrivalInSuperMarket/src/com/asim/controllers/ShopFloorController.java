package com.asim.controllers;

import com.asim.interfaces.Observable;
import com.asim.interfaces.Observer;
import com.asim.shopper.ShopperModel;
import com.asim.shopper.ShopperSpawnRate;

import java.util.LinkedList;
import java.util.Timer;

public class ShopFloorController implements Observable {

    //Store multiple instances of Shopper Object
    static LinkedList<ShopperModel> shoppers = new LinkedList<>();

    Timer shopperTimer = new Timer(true);

    static private int shoppersEntered = 0;

    private void ShopperBehaviour (){

        shoppersEntered++;

        //For tracking customer //
        this.shoppers.get(shoppers.size() - 1).setShopperNum(shoppersEntered);

        //Start customer behavior//
       shopperTimer.schedule(this.shoppers.get(shoppers.size() - 1), 0);

    }


    @Override
    public void RegisterObserver(ShopperModel shopper) {

        this.shoppers.add(shopper);


        //System.out.println("Shopper " + shopperSpawnRate.getShoppersEntered() + " has entered the in store"); //Size shows it increments

        ShopperBehaviour ();

    }

    @Override
    public void RemoveObserver(Observer shopper) {

        shoppers.remove(shopper);

    }

    @Override
    public void NotifyObservers() {

    }
}




