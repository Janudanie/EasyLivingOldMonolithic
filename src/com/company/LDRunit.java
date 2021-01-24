package com.company;

import java.util.Date;

public class LDRunit {

    private String name = "LDR";
    private long lastAlarm;


    public void generateAlarm(){
        lastAlarm = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }


    public boolean testAlarm(){
        long currentTime = System.currentTimeMillis();
        if(currentTime >= (lastAlarm + 600*1000)){
            return false;
        }
        return true;
    }
}
