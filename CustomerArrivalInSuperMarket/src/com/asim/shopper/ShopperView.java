package com.asim.shopper;

import com.asim.controllers.ShopFloorController;
import com.asim.controllers.ShopperController;
import com.asim.interfaces.Observer;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class ShopperView implements Observer {



    //controllers Instanciated
    static ShopperController shopperController = new ShopperController();
    static ShopFloorController shopFloorController = new ShopFloorController();


    Scanner scanner = new Scanner(System.in);


    //Queue Size Min/Max
    private static int queueSize = 0;
    private static int minQueueSize = 10;
    private static int maxQueueSize = 0;

    //For Averaging QueueSize
    static ArrayList<Integer> queueSizeArrayList = new ArrayList<Integer>();
    private static int sumOfQueue;

    //Simulation Time
    private static int simulationTimeinHour;
    private static int simulationTimeinMilliSeconds;


    public void AskSimulationParameters(){

        System.out.println("Please enter the number of hours to the Simulate Supermarket?");

        //3600 ms is 1 hour
        simulationTimeinHour = scanner.nextInt();
        simulationTimeinMilliSeconds = simulationTimeinHour * 3600;

        //Ask about rate of Shopper entry
        System.out.printf("Please enter the number of customers to simulate per minute? (3600 is max customer limit per hour) "); //3600 is the limit because cannot make customer faster than 1 ms because of timer limitations
        //(60 milliseconds == 1 min) div by number of customer per min
        long shoppersPerMin = 60/scanner.nextInt();


        System.out.println("Shoppers per minute" + shoppersPerMin);
        shopperController.setArrivalRate(shoppersPerMin);
    }


    public static int getSimulationTime() {
        System.out.println("Simulation Time MilliSeconds: " + simulationTimeinMilliSeconds);
        return simulationTimeinMilliSeconds;

    }

    @Override
    public void Update(Queue<ShopperModel> queue) {

        queueSize = queue.size();
         queueSizeArrayList.add(queueSize);

        if (queueSize > maxQueueSize){
            maxQueueSize = queueSize;
        }else if (queueSize < minQueueSize && queueSize > 0){
            minQueueSize = queueSize;
        }

    }

   public static void PrintShopFloorStats(){

        for(int queue: queueSizeArrayList){

            sumOfQueue += queue;
        }

       ShopperModel shopperModel = new ShopperModel();

       System.out.println("Min Queue Size: " + minQueueSize);

       System.out.println("Average Queue Size " + sumOfQueue / queueSizeArrayList.size());

       System.out.println("Max Queue Size: " + maxQueueSize);


       System.out.println("\nTotal Shoppers Entered: " + shopperController.getShoppersEntered() + " in " + simulationTimeinHour + " hour duration.");

       int totalShoppersServed = shopperModel.getTotalShoppersServed();
       System.out.println("Total Shoppers Served:  " + totalShoppersServed);

       //Throughput Formula: Amount of Customer Servered / Simulation Time(Minutes)
       float throughput = (float)totalShoppersServed/(60 * simulationTimeinHour);
       System.out.printf("\nSimulation's Throughput: %.2f", throughput);

   }


}

    //Print Results for Throughput , Customers







