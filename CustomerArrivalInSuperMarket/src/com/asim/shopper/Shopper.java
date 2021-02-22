package com.asim.shopper;

import com.asim.Products;

import java.util.ArrayList;
import java.util.TimerTask;

public class Shopper extends TimerTask{

    Products products = new Products();

    //Storing unique products for each customer
    ArrayList<String> shoppersBasket = new ArrayList<String>();

    private int shopperNum;

  public void PickUpItems(){

      for (String product: products.getRandomProducts()) {
          shoppersBasket.add(product);
      }

          //Each product == 1 sec
      int delay = (shoppersBasket.size() * 1000);


      try {
          Thread.sleep(delay); //1 sec per item
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      JoinQueue();

  }


   public void JoinQueue(){

       System.out.println("Shopper " + shopperNum + " has joined the queue with basket of " + shoppersBasket);

   }

    @Override
    public void run() {

        PickUpItems();
    }


    public void setShopperNum(int num){
      this.shopperNum = num;
    }


}

//Method to Bag Items
//Method to leave ShopFloor
