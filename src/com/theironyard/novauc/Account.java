package com.theironyard.novauc;

import java.util.HashMap;

public class Account {

    String name;
    String password;
    double checkingBalance;
    double savingBalance;

    //This section is for Getters and Setters
    //Some of the setters are not fully being used so far
    //Such as set name and set password
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

    //These are two constructors for use in other classes
    //One is empty and the other is set to gather the overloaded uses
    public Account() {
    }
    public Account(String name, String password, double checkingBalance, double savingBalance) {
        this.name = name;
        this.password = password;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;

    }

    //This is the constructor for the HashMap setting up the Key = a String that dictates the loginName
    //And the value section is set up to hold objects from the Accounts class
    public static HashMap<String, Account> information = new HashMap<>();

    //Not sure if this method is being properly used
//    //TODO run debug mode to find if and where this program is being called from
//    public Account transferPerson (String transferPerson) {
//        return Account.information.get(transferPerson);
//    }

    //Not sure if this method is being properly used
    //TODO run debug mode to find if and where this program is being called from
    public Account currentUser(String logName){
        return Account.information.get(logName);
    }

    //This is a small method that sets up the first few accounts one of which is an administrator account
    public void startUP() throws Exception {

        information.put("ADMINISTRATOR", new Account("Administrator", "admin", 1000000.99,14234.44));
        information.put("ALVIN", new Account("Alvin", "alvin", 2000.32,453453.32));
        information.put("MIKE", new Account("Mike", "mike", 3442.75,34423.44));
        information.put("WILL", new Account("Will", "will", 9437.44,3344.32));

    }

    //This is the welcome message method it has 4k graphics and very demanding of the cpu
    //This is to distinguish between the regular customer and an administrator login
    public void welcomeMessage()throws Exception{
        System.out.println("###############      HELLO AND WELCOME      ################");
        System.out.println("###############       BANK OF AMERICA       ################");
        System.out.println("####                                                    ####");
        System.out.println("####     [CUSTOMER LOGIN]     [ADMINISTRATOR LOGIN]     ####");
        System.out.println("####                                                    ####");
        int runLoop=1;
        while (runLoop==1){
            String whichLogin=Main.kb.nextLine();
            if(whichLogin.equalsIgnoreCase("customer login")){
                password();
            }
            else if(whichLogin.equalsIgnoreCase("administrator login")){
                adminLogin();
            }
        }
    }

    //This is the administrator login this has the ability to create any new user and delete any user
    //Which if the program works correctly only a certain user can create and delete their own account
    public void adminLogin() throws Exception {
        System.out.print("Please enter your the administrator log-in name: ");
        String adminLogin = Main.kb.nextLine().toUpperCase();

        if (adminLogin.equalsIgnoreCase(information.get("ADMINISTRATOR").getName())) {
            System.out.print("Please enter your password: ");
            String password = Main.kb.nextLine().toLowerCase();
            if (password.equals(information.get("ADMINISTRATOR").getPassword())) {
                Main.program.AdminProgram("ADMINISTRATOR");
            } else {
                int attempts = 3;

                while (attempts != 0 || !password.equals(information.get("ADMINISTRATOR").getPassword())) {
                    System.out.println("You have entered the wrong password you have "
                            + attempts + " more attempts left");
                    System.out.print("Please enter your correct password: ");
                    password = Main.kb.nextLine();
                    attempts--;
                }
                if (attempts == 0) {
                    password();
                } else {
                    Main.program.AdminProgram("ADMINISTRATOR");
                }
            }
        }
    }

    //This is a password verifier meant for customer login and uses most of the same methodology as the admin login
    public void password() throws Exception {
        int bugFixLoop = 1;
        while (bugFixLoop == 1) {
            System.out.print("Please enter your log-in name: ");
            String logName = Main.kb.nextLine().toUpperCase();

            if (logName.equalsIgnoreCase("") || logName == null || logName.isEmpty()) {
                System.out.println("You have entered a wrong name");
                password();
            } else if ((information.containsKey(logName))) {
                System.out.print("Please enter your password: ");
                String password = Main.kb.nextLine().toLowerCase();
                if (password.equals(information.get(logName).getPassword())) {
                    Main.program.Program(logName);
                } else {
                    int attempts = 3;
                    while (attempts != 0 && !password.equals(information.get(logName).getPassword())) {
                        System.out.println("You have entered the wrong password you have "
                                + attempts + " more attempts left");
                        System.out.println("Please enter your correct password: ");
                        password = Main.kb.nextLine();
                        attempts--;
                    }
                    if (attempts <= 0) {
                        System.out.println("You have no more attempts. You will now go to the home screen");
                        welcomeMessage();
                    } else {
                        Main.program.Program(logName);
                    }
                }
            } else {
                System.out.println("Have entered a name not inside this database");
                System.out.println("Would you like to create a new account [Y/N]");
                String userAnswer = Main.kb.nextLine();
                createUser(userAnswer);
            }
        }
    }

    //This is a create user function only accessible to create a new user for themselves or an admin can create any
    public void createUser(String response)throws Exception {
        if (response.equalsIgnoreCase("n")) {
            System.out.println("Thank you for your patronage.");
            System.exit(0);
        }
        else {
            System.out.println("Please enter the new username you would like to create");
            String createUser = Main.kb.nextLine();

            System.out.println("Default password setup as username");

            System.out.println("Please enter the current checking balance in that account");
            double createCheckBal = Main.kb.nextDouble();

            System.out.println("Please enter the current savings balance in that account");
            double createSavingBal = Main.kb.nextDouble();

            information.put(createUser, new Account(createUser.toUpperCase(), createUser.toLowerCase(), createCheckBal,createSavingBal));
            System.out.println("###############      NEW USER CREATED      ################");
        }
        password();
    }

    //
    public void deleteUser()throws Exception{
        System.out.println("Please enter the username you would like to delete");
        Main.kb.nextLine();
        String deleteThisUser = Main.kb.nextLine().toUpperCase();
        System.out.println();
        System.out.println("You are about to delete "+ information.get(deleteThisUser).getName()+"s' account");
        System.out.println("That account holds the current balances of");
        System.out.printf("## [Checking Account] $%.2f   [Savings Account] $%.2f ##",
                information.get(deleteThisUser).getCheckingBalance(),
                information.get(deleteThisUser).getSavingBalance());
        System.out.println();
        int deletingChoice=3;
        while(deletingChoice>0) {
            System.out.println("Last Chance. Please confirm that you would like to continue deleting "
                    + information.get(deleteThisUser).getName() + "s' account [Y/N]");
            String deleteChoice = Main.kb.nextLine();
            if(deleteChoice.equalsIgnoreCase("y")){
                information.remove(deleteThisUser);
                System.out.println(deleteThisUser.toLowerCase()+"'s Account has been deleted");
                System.out.println("You will now be returning to the main menu");
                welcomeMessage();
            }
            else if(deleteChoice.equalsIgnoreCase("n")){
                System.out.println("Deleting account canceled");
                System.out.println("You wil now return to the main login");
                welcomeMessage();
            }
            else
                deletingChoice--;
        }
        Main.program.cancel();
    }
}
