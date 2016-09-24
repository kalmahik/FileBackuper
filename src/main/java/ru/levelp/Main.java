package ru.levelp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Menu.getInstance().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//Users/kalmahik/Desktop/2-6