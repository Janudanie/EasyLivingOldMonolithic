package com.company;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Unit {

    private Controller masterForMe = null;
    private int pinconnected;
    //pinMode 0=INput, 1=OUTPUT
    private String name;
    private int pinMode;
    private String option1;
    private String option2;
    private String sendIfAlarm;
    private Unit sendAlarmTo;


    public Unit(String name, int pinMode, String option1, String option2) {
        this.name = name;
        this.pinMode = pinMode;
        this.option1 = option1;
        this.option2 = option2;
    }


    public String getName() {
        return name;
    }

    public int getPinMode() {
        return pinMode;
    }

    public void setAlarm(Unit u){
        //set up alarm if mode is 0
        if(pinMode == 0){
            sendAlarmTo = u;
        }
        else{
            System.out.println("Not a input unit");
        }
    }

    public void generateAlarm(){
        if(sendAlarmTo!=null /*&& Main.alextest.testAlarm()*/){
            sendAlarmTo.alarm();
        }
    }


    public void alarm(){
        if(pinMode == 1){
            String message = masterForMe.getMacAdd() + ":" + (pinconnected-1) + "1" + option1 + option2;
            SimpleMqttClient.publishThis("ledStatus",message);
        }
    }

    public void setMasterForMe(Controller masterForMe) {
        this.masterForMe = masterForMe;
    }

    public Controller getMasterForMe() {
        return masterForMe;
    }

    public void setPinconnected(int pinconnected) {
        this.pinconnected = pinconnected;
    }

    public Unit getSendAlarmTo() {
        return sendAlarmTo;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }
}
