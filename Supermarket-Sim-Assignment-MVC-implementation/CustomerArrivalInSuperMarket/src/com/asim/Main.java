package com.asim;

import com.asim.shopper.ShopperSpawnRate;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws IOException {

        //Get info from User at start of Simulation
        View shopperView = new View();
        shopperView.AskSimulationParameters();


     TimerTask shopperArrivalRate = new ShopperSpawnRate();

     //Get Products from CVS File
     Products products = new Products();
     products.ReadProductsFromCVSFile();

    //..Run timers as Daemon(in background == true)..//
     Timer timer1 = new Timer(true); //True == allow background processing


//Start simulation with Task, delays and interval of simulation
     System.out.println("Start Sending Shoppers to SuperMarket");

     timer1.scheduleAtFixedRate(shopperArrivalRate, 0 , 1); //100 == 1ms 1000 == 1 sec


   //Halt Main Class

     try{
         Thread.sleep(shopperView.getSimulationTime());
     } catch (InterruptedException e) {
         e.printStackTrace();
     }

     //Stop simulation
        timer1.cancel();


        try{
            Thread.sleep(1200);
        } catch (InterruptedException e){

        }

        System.out.println("\n<<Simulation Ended>>");

        shopperView.PrintShopFloorStats();


    }
}
