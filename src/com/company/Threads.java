package com.company;

public class Threads {

    public static class MyRunnAble implements Runnable{

        @Override
        public void run() {
            while(true) {
                System.out.println("test");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }





    public static void main(String[] args){
        Thread mytestthread = new Thread(new MyRunnAble());
        mytestthread.start();


        while(true){
            System.out.println("test20");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
