package com.asim;
import java.util.LinkedList;


public class Bag {

    static LinkedList <String> bagedItems = new LinkedList<String>();

    public void add(String item) {

    bagedItems.add(item);

    }

    public int GetBaggedProducts() {
        return bagedItems.size();

    }

}