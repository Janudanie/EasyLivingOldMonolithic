package com.company;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static ArrayList<Controller> controllerArrayList = new ArrayList<>();
    public static ArrayList<Unit> unitArrayList = new ArrayList<>();
    public static LDRunit alextest = new LDRunit();

    public static void main(String[] args) {
        //Statiske enheder, for at let at få enheder ind
        Main.controllerArrayList.add(new Controller("bcddc2235bdb"));
        Main.unitArrayList.add(new Unit("led",1,"00010","00000"));
        Main.unitArrayList.add(new Unit("pir",0,"00001","00000"));

        //Start MQTT server
        SimpleMqttClient MQTTSERVER = new SimpleMqttClient();
        Thread smc = new Thread(MQTTSERVER);
        smc.start();

        //tilføj en ldr unit statisk, indtil programmet kan klare dette selv
        Controller k = Methods.findController("bcddc2235bdb");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        k.setPin8(alextest);
        String messageToNode2 =  "bcddc2235bdb:700001000010";
        SimpleMqttClient.publishThis("ledStatus",messageToNode2);

        //bruger menu valg
        int menuChoise = 0;
        while (menuChoise != 10) {
            printMenu();
            menuChoise = readChoise();
            Unit tempUnit1;
            Unit tempUnit2;
            Controller tempController;
            Scanner scanner = new Scanner(System.in);
            String search;
            switch (menuChoise){

                case 1: //Create Controller
                    Methods.createController();
                    break;

                case 2: //Create Unit
                    Methods.createUnit();
                    break;

                case 3: //assign unit to controller
                    System.out.println("Find Controller macAdd:");
                    search = scanner.nextLine();
                    tempController=Methods.findController(search);

                    if(tempController==null){
                        break;
                    }

                    System.out.println("Find unit name:");
                    search = scanner.nextLine();


                    tempUnit1=Methods.findUnit(search);
                    if(tempUnit1==null){
                        break;
                    }
                    System.out.println("What pin:");
                    int testing = readChoise();
                    switch (testing){
                        case 1:
                            tempController.setPin1(tempUnit1);
                            break;
                        case 2:
                            tempController.setPin2(tempUnit1);
                            break;
                        case 3:
                            tempController.setPin3(tempUnit1);
                            break;
                        case 4:
                            tempController.setPin4(tempUnit1);
                            break;
                        case 5:
                            tempController.setPin5(tempUnit1);
                            break;
                        case 6:
                            tempController.setPin6(tempUnit1);
                            break;
                        case 7:
                            tempController.setPin7(tempUnit1);
                            break;
                        /*case 8:
                            tempController.setPin8(tempUnit1);
                            break;*/
                    }




                    tempUnit1.setMasterForMe(tempController);
                    tempUnit1.setPinconnected(testing);
                    String messageToNode = tempController.getMacAdd() + ":" + (testing-1) + tempUnit1.getPinMode();
                    messageToNode += tempUnit1.getOption1();
                    messageToNode += tempUnit1.getOption2();
                    SimpleMqttClient.publishThis("ledStatus",messageToNode);
                    break;

                case 4: //remove unit from a controller
                    System.out.println("Find unit name:");
                    search = scanner.nextLine();
                   tempUnit1=Methods.findUnit(search);
                   if(tempUnit1.getMasterForMe()!=null) {
                       tempUnit1.setMasterForMe(null);
                   }
                   else {
                       System.out.println("denne unit er ikke tilknyttet en controller");
                       break;
                   }
                   for(Controller K : controllerArrayList ){
                       if(K.getPin1() == tempUnit1 ) {K.setPin1(null);}
                       if(K.getPin2() == tempUnit1 ) {K.setPin2(null);}
                       if(K.getPin3() == tempUnit1 ) {K.setPin3(null);}
                       if(K.getPin4() == tempUnit1 ) {K.setPin4(null);}
                       if(K.getPin5() == tempUnit1 ) {K.setPin5(null);}
                       if(K.getPin6() == tempUnit1 ) {K.setPin6(null);}
                       if(K.getPin7() == tempUnit1 ) {K.setPin7(null);}
                       //if(K.getPin8() == tempUnit1 ) {K.setPin8(null);}
                   }
                    break;

                case 5: //assign alarm
                    //SimpleMqttClient.publishThis("ledStatus","hello world");

                    System.out.println("Find unit input alarm:");
                    search = scanner.nextLine();


                    tempUnit1=Methods.findUnit(search);
                    if(tempUnit1==null){
                        break;
                    }
                    System.out.println("Find unit output alarm:");
                    search = scanner.nextLine();


                    tempUnit2=Methods.findUnit(search);
                    if(tempUnit2==null){
                        break;
                    }


                    if(tempUnit1.getPinMode() == 0 && tempUnit2.getPinMode() == 1) {
                        tempUnit1.setAlarm(tempUnit2);
                        System.out.println("alarm er sat op");
                    }
                    else {
                        System.out.println("dette kan ikke lade sig gøre");
                    }

                    break;

                case 6: //list all controllers
                    Methods.listAllController();
                    break;

                case 7: //list all units
                    Methods.listAllUnit();
                    break;

                case 8: //remove an controller
                    System.out.println("Find Controller macAdd:");
                    search = scanner.nextLine();
                    tempController=Methods.findController(search);

                    if(Methods.removeController(tempController) && tempController != null ) {
                        System.out.println("Object er slettet");
                    }
                    else {
                        System.out.println("Object ikke fundet");
                    };
                    break;

            }

        }
        //stop MQTT thread
        MQTTSERVER.stop();

    }

    public static int readChoise(){
            System.out.println("-----------");
            System.out.println("What is your choise?");
            Scanner sc = new Scanner(System.in);
            int choise = 0;
            try {
                choise = sc.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Sorry, that was not an valid input");
            }
            return choise;
        }

    public static void printMenu(){
        System.out.println("1 New controller");
        System.out.println("2 New unit");
        System.out.println("3 Assign Unit to controller");
        System.out.println("4 Remove Unit from controller");
        System.out.println("5 assign alarm to unit");
        System.out.println("6 list all controller");
        System.out.println("7 list all units");
        System.out.println("8 Delete Controller");
        System.out.println("9 Delete Unit");
        System.out.println("10 Exit");
    }
}