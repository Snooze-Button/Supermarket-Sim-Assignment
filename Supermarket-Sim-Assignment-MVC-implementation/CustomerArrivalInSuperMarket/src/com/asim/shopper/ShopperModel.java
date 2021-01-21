package com.asim.shopper;
import com.asim.Bag;
import com.asim.Products;
import com.asim.Tills;
import com.asim.controllers.QueueController;
import com.asim.controllers.ShopFloorController;
import com.asim.interfaces.Observer;

import java.util.ArrayList;
import java.util.Queue;
import java.util.TimerTask;

public class ShopperModel extends TimerTask implements Observer {

    Products products = new Products();

    //Storing unique products for each customer
    ArrayList<String> shoppersBasket = new ArrayList<String>();

    //QueueController queue;
    Tills till;
    ShopFloorController shopFloorController;

    Bag bag = new Bag();

    private int shopperNum;
    private int delay;

    //total customer server
    private static int totalShoppersServed = 0;

    public int getTotalShoppersServed() {

        return totalShoppersServed;

    }

    private void PickUpItems(){

      //Get random products with diff sizes from product class
      for (String product: products.getRandomProducts()) {
          shoppersBasket.add(product); //stores random products in the arraylist
      }

      delay = (shoppersBasket.size() * 5); // 5 sec pick up per item

      //Problem with thread.sleep it halts all objects behavior in the class
      try {
          Thread.sleep(delay); //1 sec per item
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      JoinQueue();
  }


   private void JoinQueue(){

        //queue = new Queue();
       till = new Tills();


       System.out.println("Shopper " + shopperNum + " has joined the queue with basket of " + shoppersBasket);

       //Create new queue object
       //queue = new QueueController();
       //register this Shopper Object in queue
       till.addShopperToQueue(this);

   }

    @Override
    public void run() {

        PickUpItems();
    }

    public void setShopperNum(int num){
      this.shopperNum = num;
    }

    public int getItemsDelay(){
      return delay;
  }


    @Override
    public void Update(Queue queue) {
      // System.out.println("Shopper " + shopperNum + " is bagging items now");
       //Fixed multiple bagging and fin bagging problem

        queue.remove(this);  //used same queue object to register and remove shopper from queue

        BagItems();
    }


    //Testing Synchronized //
    private void BagItems(){

        for (String product: shoppersBasket) {
            try {
                bag.add(product); // sends to bag item
                Thread.sleep(delay/4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("Shopper " + shopperNum + " has fin bagging item and is ready to leave the store");

        ExitStore();

    }

    //Testing Synchronised
    public void ExitStore(){

      shopFloorController = new ShopFloorController();

        totalShoppersServed++;
        System.out.println("<< Shopper left "+ totalShoppersServed +" >>");

      shopFloorController.RemoveObserver(this);


    }


}
//Method to leave ShopFloor //remove shopper object from ShopFloor's static LinkedList
