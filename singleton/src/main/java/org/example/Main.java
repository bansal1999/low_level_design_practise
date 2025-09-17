package org.example;

class Singleton {
    private static Singleton single_instance = null;
    public String s;

    private Singleton() {
        s = "Sting from Singleton class";
    }

    public static Singleton getInstance() {
        if (single_instance == null) {
            single_instance = new Singleton();
        }
        return single_instance;
    }

}

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");


        Singleton x = Singleton.getInstance();
        Singleton y =  Singleton.getInstance();

        x.s = (x.s).toUpperCase();

    }
}