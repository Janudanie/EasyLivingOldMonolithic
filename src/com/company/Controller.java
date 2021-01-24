package com.company;

public class Controller {
    private String MacAdd;

    private Unit pin1 = null;
    private Unit pin2 = null;
    private Unit pin3 = null;
    private Unit pin4 = null;
    private Unit pin5 = null;
    private Unit pin6 = null;
    private Unit pin7 = null;
    private LDRunit pin8 = null;


    public Controller(String macAdd) {
        MacAdd = macAdd;
    }

    public String getMacAdd() {
        return MacAdd;
    }


    public void setPin1(Unit pin1) {
        this.pin1 = pin1;
    }

    public void setPin2(Unit pin2) {
        this.pin2 = pin2;
    }

    public void setPin3(Unit pin3) {
        this.pin3 = pin3;
    }

    public void setPin4(Unit pin4) {
        this.pin4 = pin4;
    }

    public void setPin5(Unit pin5) {
        this.pin5 = pin5;
    }

    public void setPin6(Unit pin6) {
        this.pin6 = pin6;
    }

    public void setPin7(Unit pin7) {
        this.pin7 = pin7;
    }

    public void setPin8(LDRunit pin8) {
        this.pin8 = pin8;
    }

    public Unit getPin1() {
        return pin1;
    }

    public Unit getPin2() {
        return pin2;
    }

    public Unit getPin3() {
        return pin3;
    }

    public Unit getPin4() {
        return pin4;
    }

    public Unit getPin5() {
        return pin5;
    }

    public Unit getPin6() {
        return pin6;
    }

    public Unit getPin7() {
        return pin7;
    }

    public LDRunit getPin8() {
        return pin8;
    }
}
