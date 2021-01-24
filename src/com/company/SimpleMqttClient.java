package com.company;
import static java.lang.Thread.currentThread;
import org.eclipse.paho.client.mqttv3.*;

class SimpleMqttClient implements MqttCallback,Runnable {

    static MqttClient myClient;
    MqttConnectOptions connOpt;

    static final String BROKER_URL = "tcp://192.168.1.245:1883";
    static final String M2MIO_DOMAIN = "ledStatus";
    static final String M2MIO_STUFF = "things";
    static final String M2MIO_THING = "<alex>";
    static final String M2MIO_USERNAME = "<m2m.io username>";
    static final String M2MIO_PASSWORD_MD5 = "<m2m.io password (MD5 sum of password)>";

    public volatile boolean stopThread = false;
    // the following two flags control whether this example is a publisher, a subscriber or both
    static final Boolean subscriber = true;
    static final Boolean publisher = false;

    /**
     *
     * connectionLost
     * This callback is invoked upon losing the MQTT connection.
     *
     */
    @Override
    public void connectionLost(Throwable t) {
        System.out.println("Connection lost!");
        // code to reconnect to the broker would go here if desired
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String message= new String(mqttMessage.getPayload());

        //Tester om det modtaget er stort nok
        if(message.length() < 14 ){
            return;
        }

        //udtager mac fra modtaget besked
        String tempMac = message.substring(0,12);
        //udtager pin nummer fra besked
        String temppin = message.substring(13,14);
        Controller tempController;
        tempController=Methods.findController(tempMac);

        if(tempController==null){
            //ikke en valid controller
            return;
        }

        if ( temppin.equals("0") && tempController.getPin1() != null){
            tempController.getPin1().generateAlarm();
            return;
        }

        if ( temppin.equals("1") && tempController.getPin2() != null){
            tempController.getPin2().generateAlarm();
            return;
        }

        if ( temppin.equals("2") && tempController.getPin3() != null){
            tempController.getPin3().generateAlarm();
            return;
        }

        if ( temppin.equals("3") && tempController.getPin4() != null){
            tempController.getPin4().generateAlarm();
            return;
        }

        if ( temppin.equals("4") && tempController.getPin5() != null){
            tempController.getPin5().generateAlarm();
            return;
        }

        if ( temppin.equals("5") && tempController.getPin6() != null){
            tempController.getPin6().generateAlarm();
            return;
        }

        if ( temppin.equals("6") && tempController.getPin7() != null){
            tempController.getPin7().generateAlarm();
            return;
        }

        if (temppin.equals("7") && tempController.getPin8() != null){
            tempController.getPin8().generateAlarm();
            return;
        }
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    public static void publishThis(String mytopic,String msg){
        MqttTopic topic = myClient.getTopic(mytopic);
        MqttMessage message = new MqttMessage(msg.getBytes());
        int pubQoS = 0;
        message.setQos((pubQoS));
        message.setRetained(false);
        try {
            topic.publish(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 /*   public static void main(String[] args) {
        SimpleMqttClient smc = new SimpleMqttClient();
        smc.runClient();

    }*/

    /**
     *
     * runClient
     * The main functionality of this simple example.
     * Create a MQTT client, connect to broker, pub/sub, disconnect.
     *
     */
    //public void runClient()


   public void stop() {stopThread = true;}


    @Override
    public void run() {

        // setup MQTT Client
        String clientID = M2MIO_THING;
        connOpt = new MqttConnectOptions();

        connOpt.setCleanSession(true);
        //connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(M2MIO_USERNAME);
        connOpt.setPassword(M2MIO_PASSWORD_MD5.toCharArray());

        // Connect to Broker
        try {
            myClient = new MqttClient(BROKER_URL, clientID);
            myClient.setCallback(this);
            myClient.connect(connOpt);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Connected to " + BROKER_URL + " ");

        // setup topic
        // topics on m2m.io are in the form <domain>/<stuff>/<thing>
        String myTopic = "ledUpdate";
        MqttTopic topic = myClient.getTopic(myTopic);

        // subscribe to topic if subscriber
        if (subscriber) {
            try {
                int subQoS = 0;
                myClient.subscribe(myTopic, subQoS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


/*
        // publish messages if publisher
        if (publisher) {
            for (int i=1; i<=10; i++) {
                String pubMsg = "{\"pubmsg\":" + i + "}";
                int pubQoS = 0;
                MqttMessage message = new MqttMessage(pubMsg.getBytes());
                message.setQos(pubQoS);
                message.setRetained(false);

                // Publish the message
                System.out.println("Publishing to topic \"" + topic + "\" qos " + pubQoS);
                MqttDeliveryToken token = null;
                try {
                    // publish message to broker
                    token = topic.publish(message);
                    // Wait until the message has been delivered to the broker
                    token.waitForCompletion();
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // disconnect
        try {
            // wait to ensure subscribed messages are delivered
            if (subscriber) {
                Thread.sleep(50000);
            }
            //myClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        while(!stopThread) {
            //keep server running
        }
        try {
            myClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}