package com.asim;

import com.asim.Bag;
import com.asim.Tills;
import com.asim.controllers.ShopFloorController;
import com.asim.interfaces.Observer;
import com.asim.shopper.ShopperModel;
import com.asim.shopper.ShopperSpawnRate;

import java.util.Queue;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class View implements Observer {

    //controllers Instanciated
    static ShopperSpawnRate shopperController = new ShopperSpawnRate();
    static ShopFloorController shopFloorController = new ShopFloorController();

    //Class Instanciated
    Bag bag = new Bag();
    Tills tills = new Tills();

    //Date
    java.util.Date date=new java.util.Date();


    Scanner scanner = new Scanner(System.in);


    //For Averaging QueueSize
    //static ArrayList<Integer> queueSizeArrayList = new ArrayList<Integer>();
    //private static int sumOfQueue;
    //Queue Size Min/Max
    /*private static int queueSize = 0;
    private static int minQueueSize = 10;
    private static int maxQueueSize = 0;*/

    //Variables to Print
    private static int totalShoppersServed;
    private static float throughput;

    //Simulation Time
    private static int simulationTimeinHour;
    private static int simulationTimeinMilliSeconds;

    //Till and Queue variables
    private int maxTills;
    private int maxQueue;


    public void AskSimulationParameters(){

        System.out.printf("Please enter the number of hours to the Simulate Supermarket? ");

        //3600 ms is 1 hour
        simulationTimeinHour = scanner.nextInt();
        simulationTimeinMilliSeconds = simulationTimeinHour * 3600;

        //Ask about rate of Shopper entry
        System.out.printf("Please enter the number of customers to simulate per minute? (3600 is max customer limit per hour) "); //3600 is the limit because cannot make customer faster than 1 ms because of timer limitations
        //(60 milliseconds == 1 min) div by number of customer per min
        long shopperIntervalPerMS  = 60/scanner.nextInt();

        //System.out.println("Shoppers per minute " + hopperIntervalPerMS );
        shopperController.setArrivalRate(shopperIntervalPerMS );

        //Number of max tills
        System.out.printf("Please enter Max number of tills: ");
        maxTills = scanner.nextInt();

        // Queue size
        System.out.printf("Please enter max Queue Size for the tills: ");
        maxQueue = scanner.nextInt();

        tills.setMaxTills(maxTills);
        tills.setMaxQueueSize(maxQueue);

    }

    //Simulation time to main thread.sleep//
    public static int getSimulationTime() {
        System.out.println("Simulation Time MilliSeconds: " + simulationTimeinMilliSeconds);
        return simulationTimeinMilliSeconds;

    }

    @Override
    public void Update(Queue queue) {

       /* queueSize = queue.size();
         queueSizeArrayList.add(queueSize);

        if (queueSize > maxQueueSize){
            maxQueueSize = queueSize;
        }else if (queueSize < minQueueSize && queueSize > 0){
            minQueueSize = queueSize;
        }*/

    }

   public void PrintShopFloorStats(){

     /*   for(int queue: queueSizeArrayList){

            sumOfQueue += queue;
        }
*/


       System.out.println("\nISHOP Simulation Result ( " + date + " )");

       ShopperModel shopperModel = new ShopperModel();


/*     Dont need since adding max queue and max till parameters
        System.out.println("\nMin Queue Size: " + minQueueSize);

       System.out.println("Average Queue Size " + sumOfQueue / queueSizeArrayList.size());

       System.out.println("Max Queue Size: " + maxQueueSize);*/


       System.out.println("\nTotal Shoppers Entered: " + shopperController.getShoppersEntered() + " in " + simulationTimeinHour + " hour duration.");

       totalShoppersServed = shopperModel.getTotalShoppersServed();
       System.out.println("Total Shoppers Served:  " + totalShoppersServed);
       System.out.println("Total Products Sold : " + bag.GetBaggedProducts());
       System.out.println("\nMax Number of Tills: " + maxTills + " | Max Queue Size: " + maxQueue);

       //Throughput Formula: Amount of Customer Servered / Simulation Time(Minutes)
       throughput = (float)totalShoppersServed/(60 * simulationTimeinHour);
       System.out.printf("\nSimulation's Throughput: " + String.format( "%.2f", throughput) + " Customers Served Per Minute");
       System.out.println("");

       StoreOutputInCVSFile();

   }

   public void StoreOutputInCVSFile(){

        try{
           File file =new File("Simulation Result.txt"); //Test on diff PC
           if(!file.exists()){
               file.createNewFile();
           }
           FileWriter fw = new FileWriter(file,true); //Doesnt del old data if true
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter pw = new PrintWriter(bw);

            pw.println("\n Simulation results ( " + date + " )"); // Date is for accurate record keeping useful for management
            pw.println("\nSimulation Duration: " + simulationTimeinHour + " hour.");
            /*pw.printf("\nQueue Size:");
           pw.printf(" Min " + minQueueSize);
           pw.printf(" | Average " + sumOfQueue / queueSizeArrayList.size());
           pw.printf(" | Max " + maxQueueSize);
           pw.println("");*/
           pw.println("\nTotal Shoppers Entered: " + shopperController.getShoppersEntered() + " | Total Shoppers Served: " + totalShoppersServed + " | Total Products Sold : " + bag.GetBaggedProducts());
           pw.println("\nMax Number of Tills " + maxTills + " | Max Queue Size " + maxQueue);
           pw.printf("\nSimulation's Throughput: " + String.format( "%.2f", throughput ) + " customers served per minute");

           pw.println(" ");
           pw.println(" ");
           pw.close();

           System.out.println("\nData successfully stored in Simulation Results.txt");

       }catch(IOException e){
           System.out.println("Exception occurred:");
           e.printStackTrace();
       }
   }


   }








