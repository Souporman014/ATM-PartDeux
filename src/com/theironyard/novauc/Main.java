package com.theironyard.novauc;

import java.util.Scanner;

public class Main {

    public static Scanner kb = new Scanner(System.in);
    public static Scanner kb2 = new Scanner(System.in);
    public static Scanner kb3 = new Scanner(System.in);
    public static Scanner kb4 = new Scanner(System.in);

    public static Account User1 = new Account();
    public static ATMProgram program = new ATMProgram();


    //TODO this is a failed attempt to try and resolve the clearing the buffer
//    public static String nextLine(){
//        String line = kb.nextLine();
//        int choice = 0;
//        while (kb.hasNext()){
//            if (kb.hasNextInt()){
//                choice = kb.nextInt();
//                break;
//            } else {
//                kb.next(); // Just discard this, not interested...
//            }
//        }
//        //line = kb.nextLine();
//        return line;
//    }

    public static void main(String[] args) throws Exception {

        User1.startUP();
        User1.welcomeMessage();
        User1.password();

    }
}



