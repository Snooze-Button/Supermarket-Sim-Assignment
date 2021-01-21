package com.asim.interfaces;

import com.asim.shopper.ShopperModel;

public interface Observable {

    public void RegisterObserver(ShopperModel shopperModel);
    //public void RegisterShopFloorAsObserver(ShopFloor observer); Dont need this because Shoppers are still in store when they exit the queue
    public void RemoveObserver(Observer observer);
    public void NotifyObservers();

}
