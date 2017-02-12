package com.theironyard.novauc;

import java.util.HashMap;

public class Account {

    String name;
    String password;
    double checkingBalance;
    double savingBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public Account() {
    }

    public Account(String name, String password, double checkingBalance, double savingBalance) {
        this.name = name;
        this.password = password;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;

    }

    public Account transferPerson (String transferPerson) {
        return Account.information.get(transferPerson);

    }
    public Account currentUser(String logName){
        return Account.information.get(logName);
    }
    public static HashMap<String, Account> information = new HashMap<>();

    public void startUP() throws Exception {

        information.put("ADMINISTRATOR", new Account("Administrator", "admin", 1000000.99,14234.44));
        information.put("ALVIN", new Account("Alvin", "alvin", 2000.32,453453.32));
        information.put("MIKE", new Account("Mike", "mike", 3442.75,34423.44));
        information.put("WILL", new Account("Will", "will", 9437.44,3344.32));

    }
    public void welcomeMessage()throws Exception{
        System.out.println("###############      HELLO AND WELCOME      ################");
        System.out.println("###############       BANK OF AMERICA       ################");
        System.out.println("####                                                    ####");
        System.out.println("####     [CUSTOMER LOGIN]     [ADMINISTRATOR LOGIN]     ####");
        System.out.println("####                                                    ####");
        String whichLogin="customer login";
        while (whichLogin.equalsIgnoreCase("customer login" )|| whichLogin.equalsIgnoreCase("administrator login")){
            whichLogin=Main.kb.nextLine();
            if(whichLogin.equalsIgnoreCase("customer login")){
                password();
            }
            else if(whichLogin.equalsIgnoreCase("administrator login")){
                adminLogin();
            }
        }
    }
    public void adminLogin() throws Exception {
        System.out.print("Please enter your the administrator log-in name: ");
        String logName = Main.kb.nextLine().toUpperCase();

        if (logName.equalsIgnoreCase(information.get("administrator").getName())) {
            System.out.print("Please enter your password: ");
            String password = Main.kb.nextLine().toLowerCase();
            if (password.equals(information.get("administrator").getPassword())) {
                Main.program.Program("administrator");
            } else {
                int attempts = 3;

                while (attempts != 0 || !password.equals(information.get("administrator").getPassword())) {
                    System.out.println("You have entered the wrong password you have "
                            + attempts + " more attempts left");
                    System.out.print("Please enter your correct password: ");
                    password = Main.kb.nextLine();
                    attempts--;
                }
                if (attempts == 0) {
                    password();
                } else {
                    Main.program.Program("administrator");
                }
            }
        }
    }

    public void password() throws Exception {

        System.out.print("Please enter your log-in name: ");
        String logName = Main.kb.nextLine().toUpperCase();

        if(logName.equalsIgnoreCase("") || logName == null || logName.isEmpty()) {
            System.out.println("You have entered a wrong name");
            password();
        }
        else if(logName.equalsIgnoreCase(information.get(logName).getName())){
            //if (checkName.getName().equalsIgnoreCase(name)) {
            System.out.print("Please enter your password: ");
            String password = Main.kb.nextLine().toLowerCase();
            //String password=prePassword.toLowerCase();
            if (password.equals(information.get(logName).getPassword())) {
                Main.program.Program(logName);
            } else {
                int attempts = 3;

                while (attempts != 0 || !password.equals(information.get(logName).getPassword())) {
                    System.out.println("You have entered the wrong password you have "
                            + attempts + " more attempts left");
                    System.out.print("Please enter your correct password: ");
                    password = Main.kb.nextLine();
                    attempts--;
                }
                if (attempts==0){
                    password();
                }
                else{
                    Main.program.Program(logName);
                }
            }
        }
        else{
            System.out.println("Have not entered a name inside this database");
            System.out.println("Would you like to create a new account [Y/N]");
            String response = Main.kb.nextLine();
            createUser(response);
        }
    }


    public void createUser(String response)throws Exception {

        if (response.equalsIgnoreCase("n")) {
            System.out.println("Thank you for your patronage.");
            System.exit(0);
        } else {
            //int userLimit = 1;
            System.out.println("Please enter the new username you would like to create");
            String createUser = Main.kb.nextLine();

            System.out.println("Default password setup as username");
            String newPass = Main.kb.nextLine();

            System.out.println("Please enter the current checking balance in that account");
            double createCheckBal = Main.kb.nextDouble();

            System.out.println("Please enter the current savings balance in that account");
            double createSavingBal = Main.kb.nextDouble();

            information.put(createUser, new Account(createUser, newPass, createCheckBal,createSavingBal));
            System.out.println("###############      NEW USER CREATED      ################");
            System.out.println("################      "+createUser+"      ###############");
//            switch (userLimit) {
//                case 1:
//                    information.put(createUser, new Account(createUser, newPass, createCheckBal,createSavingBal));
//
//                    information.get(createUser).getCheckingBalance();
//
////                    Main.User1.setName(createUser);
////                    Main.User1.setPassword(newPass);
////                    Main.User1.setCheckingBalance(createCheckBal);
////                    Main.User1.setSavingBalance(createSavingBal);
//
//                    break;
//                case 2:
//
//                    Main.User2.setName(createUser);
//                    Main.User2.setPassword(newPass);
//                    Main.User2.setCheckingBalance(createCheckBal);
//                    Main.User2.setSavingBalance(createSavingBal);
//                    break;
//                case 3:
//
//                    Main.User3.setName(createUser);
//                    Main.User3.setPassword(newPass);
//                    Main.User3.setCheckingBalance(createCheckBal);
//                    Main.User3.setSavingBalance(createSavingBal);
//                    break;
//                case 4:
//
//                    Main.User4.setName(createUser);
//                    Main.User4.setPassword(newPass);
//                    Main.User4.setCheckingBalance(createCheckBal);
//                    Main.User4.setSavingBalance(createSavingBal);
//                    break;
//                default:
//                    System.out.println("The System is out of room for new Users");
//                    System.out.println("the system will now shutdown thank you for your patronage");
//                    System.exit(0);
//            }
//            userLimit++;
        }
        password();
    }
}
