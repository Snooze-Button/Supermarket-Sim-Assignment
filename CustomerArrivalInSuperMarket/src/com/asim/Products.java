package com.asim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Products {

    //Take Array from CSV File//
    String path = "C:\\Users\\asimr\\Desktop\\Advanced Programming\\Projects\\Testing Shopper Pickup\\ProductList.csv";
    String line;
    static String[] products = new String[20];
    Random rn = new Random();



    public void ReadProductsFromCVSFile() {

        try {
            //BufferedReader is like Scanner but needs a filerreader as parameter
            BufferedReader br = new BufferedReader(new FileReader(path));


            // while((line = br.readLine()) != null){
            for (int i = 0; i < 20; i++) {

                line = br.readLine();
                String[] productList = line.split(",");
                products[i] = productList[1];

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public String [] getRandomProducts() {

        //Random Basket Size
        int randomBasketSize = (1 + rn.nextInt(19)) ;

        //Stores random products
        String [] randomProducts = new String[randomBasketSize];

        for(int i = 0; i < randomProducts.length; i++) {
            randomProducts[i] = products[rn.nextInt(19)];

        }

        //Substract random products from shelves stock//

        return randomProducts;

    }

}

