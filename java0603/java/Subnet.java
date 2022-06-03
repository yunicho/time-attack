package com.sparta.week02;

public class Subnet {
    public static int id;
    public static String ip;
    public static String region;
    public static Routetable routetable;

    public Subnet(int id, String ip, String region) {
        this.id = id;
        this.ip = ip;
        this.region = region;
    }

    public void transfer(String msg) {
        routetable.startGateWay().send(msg);
    }



}
