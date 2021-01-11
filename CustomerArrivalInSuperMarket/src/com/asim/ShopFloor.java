package com.asim;

import com.asim.shopper.Shopper;

import java.util.LinkedList;
import java.util.Timer;

public class ShopFloor{

    //Store multiple instances of Shopper Object
    static LinkedList<Shopper> shoppers = new LinkedList<>();
    Timer shopperTimer = new Timer(true);
    private int increment = 0;

    public void AddNewShopper(Shopper shopper) {
        this.shoppers.add(shopper);

        System.out.println("Shopper " + this.shoppers.size() + " has entered the in store"); //Size shows it increments

        ShopperBehaviour();
    }

    public void ShopperBehaviour(){

        //For tracking customer
        this.shoppers.get(increment).setShopperNum(shoppers.size());

        //Start customer behavior
       shopperTimer.schedule(this.shoppers.get(increment), 0);

       increment++;

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



