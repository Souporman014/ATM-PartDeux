package com.theironyard.novauc;

import java.util.Scanner;

public class Main {

    public static Scanner kb = new Scanner(System.in);
    public static Account User1 = new Account();
    public static ATMProgram program = new ATMProgram();

    public static void main(String[] args) throws Exception {

        User1.startUP();
        User1.welcomeMessage();
        User1.password();

    }
}



