package com.example.domicileproject;

//This holds the address fields and will used to save to firebase.
public class addresschange {
    String usn, sn, sr, pin;

    public addresschange(String usn, String sn, String sr, String pin) {
        this.usn = usn;
        this.sn = sn;
        this.sr = sr;
        this.pin = pin;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
