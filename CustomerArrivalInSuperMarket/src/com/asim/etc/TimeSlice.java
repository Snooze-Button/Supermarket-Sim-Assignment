package com.asim.etc;

import java.util.Date;
import java.util.TimerTask;

public class TimeSlice extends TimerTask {

    //What does this do ??
    public int slices = 0;

    @Override
    public void run() {
        System.out.println("Time Slice at: " + new Date()); //Prints new date at intervals
        Slice();
    }

    /*private void Slice() throws InterruptedException { //Error when calling Slice();

        Thread.sleep(1000);

    }*/

    private void Slice(){ //Ctrl and Slash multiline comment

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }


}
