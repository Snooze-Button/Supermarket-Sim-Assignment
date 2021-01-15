package com.asim.interfaces;

import com.asim.shopper.ShopperModel;

import java.util.Queue;

public interface Observer {

    //Update Shopper Status in Queue
    public void Update(Queue<ShopperModel> queue);

}
