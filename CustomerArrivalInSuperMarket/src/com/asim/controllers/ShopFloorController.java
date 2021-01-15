package com.asim.controllers;

import com.asim.interfaces.Observable;
import com.asim.interfaces.Observer;
import com.asim.shopper.ShopperModel;

import java.util.LinkedList;
import java.util.Timer;

public class ShopFloorController implements Observable {

    //Store multiple instances of Shopper Object
    static LinkedList<ShopperModel> shoppers = new LinkedList<>();
    Timer shopperTimer = new Timer(true);


    private void ShopperBehaviour(){

        //For tracking customer
        this.shoppers.get(shoppers.size() - 1).setShopperNum(shoppers.size());

        //Start customer behavior//
       shopperTimer.schedule(this.shoppers.get(shoppers.size()-1), 0);

    }


    @Override
    public void RegisterObserver(ShopperModel shopper) {

        this.shoppers.add(shopper);


       // System.out.println("Shopper " + this.shoppers.size() + " has entered the in store"); //Size shows it increments

        ShopperBehaviour();

    }

    @Override
    public void RemoveObserver(Observer shopper) {

        shoppers.remove(shopper);

    }

    @Override
    public void NotifyObservers() {

        //use this //

    }
}
    //Method to remove shopper from shopfloor
  /*  public void RemoveShopper(){
        shoppers.removeFirst();
    }*/

//Test Passed//
   /*public void PrintShoppers() {

      System.out.println("\nNumber of Shoppers in store " + shoppers.size());

        for(Shopper shopper: shoppers){
            shopper.PickUpItems();

        }
    }*/



