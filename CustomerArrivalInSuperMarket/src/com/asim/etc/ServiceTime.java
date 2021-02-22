package com.asim.etc;

import java.util.TimerTask;

public class ServiceTime extends TimerTask {

    private int servicedTimer = 0;

    @Override
    public void run() {

        servicedTimer++;
        System.out.println("<<<<<<<<<<<<<<<<<<Customer being Served " + servicedTimer);
        ServiceTimeInterval();
    }

    public void ServiceTimeInterval(){

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }

    }


}
