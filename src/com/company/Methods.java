package com.company;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Methods {
    public static void createController(){
        String macAdd;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Mac Address : ");
        macAdd = scanner.nextLine();
        if(findController(macAdd)== null) {
            Main.controllerArrayList.add(new Controller(macAdd));
            System.out.println("Controller er oprettet");
        }
        else {
            System.out.println("Denne Controller eksistere allerede");
        }
    }

    public static void createUnit(){
        String name;
        int pin;
        String option1;
        String option2;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of unit :");
        name = scanner.nextLine();

        System.out.print("Output(1) / Input(0) : ");
        try {
            pin = scanner.nextInt();
            scanner.nextLine();}
        catch (InputMismatchException e) {
            System.out.println("Sorry, that was not an valid input");
            return;
        }

        System.out.print("Option 1 :");
            option1 = scanner.nextLine();
        System.out.print("Option 2 :");
            option2 = scanner.nextLine();
        Main.unitArrayList.add(new Unit(name,pin,option1,option2));
    }

    public static void listAllController() {
    System.out.println("Alle controller");

    for (Controller k : Main.controllerArrayList) {
        System.out.println("----------------------------");
        System.out.print("Mac : ");
        System.out.println(k.getMacAdd());

        System.out.print("Pin1 = ");
        if (k.getPin1() != null) {
            System.out.println(k.getPin1().getName());
        } else {
            System.out.println(" Not connected");
        }

        System.out.print("Pin2 = ");
        if (k.getPin2() != null) {
            System.out.println(k.getPin2().getName());
        } else {
            System.out.println(" Not connected");
        }


        System.out.print("Pin3 = ");
        if (k.getPin3() != null) {
            System.out.println(k.getPin3().getName());
        } else {
            System.out.println(" Not connected");
        }

        System.out.print("Pin4 = ");
        if (k.getPin4() != null) {
            System.out.println(k.getPin4().getName());
        } else {
            System.out.println(" Not connected");
        }

        System.out.print("Pin5 = ");
        if (k.getPin5() != null) {
            System.out.println(k.getPin5().getName());
        } else {
            System.out.println(" Not connected");
        }

        System.out.print("Pin6 = ");
        if (k.getPin6() != null) {
            System.out.println(k.getPin6().getName());
        } else {
            System.out.println(" Not connected");
        }

        System.out.print("Pin7 = ");
        if (k.getPin7() != null) {
            System.out.println(k.getPin7().getName());
        } else {
            System.out.println(" Not connected");
        }

        System.out.print("Pin8 = ");
        if (k.getPin8() != null) {
            System.out.println(k.getPin8().getName());
        } else {
            System.out.println(" Not connected");
        }
        System.out.println("----------------------------");

    }
}

    public static void listAllUnit(){
        System.out.println("Alle units");

        for(Unit k : Main.unitArrayList){
            System.out.println("----------------------------");
            System.out.print("Name : ");
            System.out.println(k.getName());
            System.out.println("pinMode : " + k.getPinMode());
            System.out.println("Option 1 : " + k.getOption1());
            System.out.println("Option 2 : " + k.getOption2());
            if(k.getMasterForMe()==null){
                System.out.println("Ingen controller tilsluttet");

            }
            else {
                System.out.print("Controller : ");
                System.out.println(k.getMasterForMe().getMacAdd());
            }

            if(k.getSendAlarmTo() != null){
                System.out.println("send alarm to");
                System.out.println(k.getSendAlarmTo().getName());
            }
            System.out.println("----------------------------");

        }

    }

    public static Controller findController(String search){
        for (Controller k : Main.controllerArrayList) {
            if (k.getMacAdd().equalsIgnoreCase(search)) {
                //System.out.print("fandt :");
                //System.out.println(search);
                return k;
            }
        }
        return null;
    }

    public static Unit findUnit(String search){


        for(Unit k : Main.unitArrayList){
            if(k.getName().equalsIgnoreCase((search))) {
                System.out.print("Fandt : ");
                System.out.println(search);
                return k;
            }
        }
        return null;

    }

    public static boolean removeController(Controller X){
        int indexNr=0;
        boolean deleteObject = false;
        for (Controller k : Main.controllerArrayList) {
            if (k == X) {
                deleteObject = true;
                break;
            }
            indexNr++;
        }

        if(deleteObject){
            Main.controllerArrayList.remove(indexNr);
            return true;
        }


        return false;
    }
}
