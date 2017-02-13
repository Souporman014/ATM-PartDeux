package com.theironyard.novauc;

public class ATMProgram {

    double quarters, dimes, nickles, pennies, remainder;
    int iQuarters,iDimes,iNickles,iPennies,bills;

    //Improved change formula was checked to make sure it works
    public void setChange(double currentWithdraw) {
        quarters = (int)(currentWithdraw / .25);
        remainder = (currentWithdraw % .25);
        if (remainder>.1&&remainder<.25){
            dimes = (int)(remainder / .10);
            remainder=(remainder%.10);
        }
        if (remainder>.05&&remainder<.1){
            nickles = (int)(remainder / .05);
            remainder = (remainder % .05);
        }
        pennies = (int)(remainder/.01);
        if (remainder>.0190)
            pennies++;
        bills=(int)(quarters/4);
        iQuarters=(int)(quarters)-(bills*4);
        iDimes=(int)dimes;
        iNickles=(int)nickles;
        iPennies=(int)pennies;
    }

    //This is a simple checkBalance method shows both accounts now
    public void checkBalance(String userKey) throws Exception {
        System.out.println("#####################  BALANCE MENU #####################");
        System.out.println("####                                                 ####");
        System.out.printf("Your current checking balance is    %.2f\n",
                Account.information.get(userKey).getCheckingBalance());
        System.out.printf("Your current savings balance is    %.2f\n",
                Account.information.get(userKey).getSavingBalance());
        System.out.println("####                                                 ####");
        while(true) {
            System.out.println("would you like to return to the main menu: Y/N ");
            String balanceBack=Main.kb.nextLine();
            if (balanceBack.equalsIgnoreCase("y")) {
                Program(userKey);
            }
            else if (balanceBack.equalsIgnoreCase("n")) {
                cancel();
            }
            System.out.println("You have not answered correctly please try again");
        }
    }

    //This is the withdraw method that takes money out of your own account and has a selector for checking/savings
    public void withdrawFunds(String userKey)throws Exception {
        System.out.println("################  WITHDRAW MENU #################");
        System.out.println();
        while (true) {
            System.out.println("Which account would you like to withdraw from");
            System.out.printf("## [Checking Account] $%.2f   [Savings Account] $%.2f ##",
                    Account.information.get(userKey).getCheckingBalance(),
                    Account.information.get(userKey).getSavingBalance());
            String whichAccount=Main.kb4.nextLine();
            if (whichAccount.equalsIgnoreCase("checking account")||whichAccount.equalsIgnoreCase("checking")){
                withdrawChecking(userKey);
            }
            else if(whichAccount.equalsIgnoreCase("savings account")||whichAccount.equalsIgnoreCase("savings")){
                withdrawSavings(userKey);
            }
            System.out.println("You have not entered a correct value please try again");
        }
    }

    //This is the method for withdrawing from checking account
    public void withdrawChecking(String userKey)throws Exception{
        System.out.println("PLEASE ENTER THE AMOUNT YOU WISH TO WITHDRAW");
        double currentWithdraw = Main.kb2.nextDouble();

        if (currentWithdraw> Account.information.get(userKey).getCheckingBalance()||currentWithdraw<=0){
            System.out.println("You have entered a larger amount than what is available");
            System.out.println("Or you have entered a number less than 0");
            System.out.println("Please enter a correct withdraw value");
            withdrawChecking(userKey);
        } else {
            System.out.println("CURRENT BALANCE:    " + Account.information.get(userKey).getCheckingBalance());
            System.out.println("\tMINUS");
            System.out.println("WITHDRAWN AMOUNT:   " + currentWithdraw);
            System.out.println("WOULD YOU LIKE TO CONTINUE WITH THE WITHDRAW: Y/N");
            System.out.println();
            while(true){
                System.out.println("WOULD YOU LIKE TO CONTINUE WITH THE WITHDRAW: Y/N");
                String response = Main.kb3.nextLine();
                if (response.equalsIgnoreCase("n")) {
                    Program(userKey);
                } else if (response.equalsIgnoreCase("y")) {
                    setChange(currentWithdraw);
                    System.out.println("YOU WILL BE RECEIVING");
                    System.out.printf("$"+bills+" DOLLARS "+iQuarters + " QUARTERS " + iDimes + " DIMES " + iNickles + " NICKLES " + iPennies + " PENNIES\n");
                    System.out.println("TRANSACTION HAS BEEN COMPLETED PLEASE TAKE YOUR MONEY");
                    double newAmount = Account.information.get(userKey).getCheckingBalance() - currentWithdraw;
                    Main.User1.information.get(userKey).setCheckingBalance(newAmount);
                    System.out.println();
                    System.out.println("#################################################");
                    System.out.println();
                    System.out.printf("CURRENT AMOUNT IN ACCOUNT IS NOW %.2f \n", newAmount);
                    System.out.println();
                    while(true){
                        System.out.println("would you like to return to the main menu: Y/N ");
                        String chooseMainMenu = Main.kb4.nextLine();
                        switch (chooseMainMenu) {
                            case "y":
                                Program(userKey);
                                break;
                            case "n":
                                cancel();
                                break;
                            default:
                                System.out.println("Please enter a correct response");
                        }
                    }
                }
                System.out.println("You did not enter a proper value please try again");
            }
        }
    }

    //Almost identical method for withdrawing from savings account
    public void withdrawSavings(String userKey)throws Exception{
        System.out.println("PLEASE ENTER THE AMOUNT YOU WISH TO WITHDRAW");
        double currentWithdraw = Main.kb2.nextDouble();
        if (currentWithdraw> Account.information.get(userKey).getSavingBalance()||currentWithdraw<=0){
            System.out.println("You have entered a larger amount than what is available");
            System.out.println("Or you have entered a number less than 0");
            System.out.println("Please enter a correct withdraw value");
            withdrawSavings(userKey);
        } else {
            System.out.println("CURRENT BALANCE:    " + Account.information.get(userKey).getSavingBalance());
            System.out.println("\tMINUS");
            System.out.println("WITHDRAWN AMOUNT:   " + currentWithdraw);
            System.out.println();
            while(true){
                System.out.println("WOULD YOU LIKE TO CONTINUE WITH THE WITHDRAW: Y/N");
                String response = Main.kb4.nextLine();
                if (response.equalsIgnoreCase("n")) {
                    System.out.println("You are returning to your account page");
                    Program(userKey);
                } else if (response.equalsIgnoreCase("y")) {
                    setChange(currentWithdraw);
                    System.out.println("YOU WILL BE RECEIVING");
                    System.out.printf("$"+bills+" DOLLARS "+iQuarters + " QUARTERS " + iDimes + " DIMES " + iNickles + " NICKLES " + iPennies + " PENNIES\n");
                    System.out.println("TRANSACTION HAS BEEN COMPLETED PLEASE TAKE YOUR MONEY");
                    double newAmount = Account.information.get(userKey).getSavingBalance() - currentWithdraw;
                    Main.User1.information.get(userKey).setSavingBalance(newAmount);
                    System.out.println();
                    System.out.println("#################################################");
                    System.out.println();
                    System.out.printf("CURRENT AMOUNT IN ACCOUNT IS NOW %.2f \n", newAmount);
                    System.out.println();
                    System.out.println("Returning to your account page");
                    Program(userKey);
                }
                System.out.println("You did not enter a proper value please try again");
            }
        }
    }

    //This is a method for choosing which account you will be transferring from very basic
    public void transferFunds(String userKey)throws Exception {
        System.out.println("###############     TRANSFER FUNDS MENU    ################");
        System.out.println();
        System.out.println("Please select which account you would like to transfer funds from");
        System.out.println();
        System.out.printf("Checking account balance %.2f\tSavings account balance %.2f\n",
                Account.information.get(userKey).getCheckingBalance(),
                Account.information.get(userKey).getSavingBalance());
        while (true) {
            System.out.println();
            System.out.println("Enter checking or savings:");
            String whichAccount = Main.kb2.nextLine();
            if (whichAccount.equalsIgnoreCase("checking")) {
                System.out.println();
                while (true) {
                    System.out.println("Please enter who you would like to transfer funds to");
                    String transferPerson = Main.kb3.nextLine().toUpperCase();
                    if (Main.User1.information.get(transferPerson).getName().equalsIgnoreCase(transferPerson)) {
                        System.out.printf("Enter the funds value you would like to send to %s\n",
                                Main.User1.information.get(transferPerson).getName());
                        double transferFunds = Main.kb4.nextDouble();
                        double newAmount = Main.User1.information.get(transferPerson).getCheckingBalance() + transferFunds;
                        Main.User1.information.get(transferPerson).setCheckingBalance(newAmount);
                        System.out.println("Funds were successfully transferred ");
                        System.out.println("Now returning to the main menu");
                        Main.program.Program(userKey);
                    }
                    else{
                        System.out.println("The name you have entered does not have an account at this bank");
                        System.out.println("Please enter a new account name");
                        transferFunds(userKey);
                    }
                }
            }else if (whichAccount.equalsIgnoreCase("savings")) {
                while (true) {
                    System.out.println("Who please enter who you would like to transfer funds to");
                    String transferPerson = Main.kb4.nextLine().toUpperCase();
                    System.out.printf("Enter the funds value you would like to send to %s\n",
                            Main.User1.information.get(transferPerson).getName());
                    double transferFunds = Main.kb2.nextDouble();
                    double newAmount = Main.User1.information.get(transferPerson).getSavingBalance() + transferFunds;
                    Main.User1.information.get(transferPerson).setSavingBalance(newAmount);
                }
            }
        }
    }

    //This is the admin program that has different options to create and delete accounts
    public void AdminProgram(String adminKey)throws Exception{
        System.out.println("############  ADMINISTRATOR MENU  ################");
        System.out.println();
        System.out.println("PRESS 1 FOR : CREATE ACCOUNT ");
        System.out.println("PRESS 2 FOR : DELETE ACCOUNT");
        System.out.println("PRESS 3 FOR : MAIN MENU");
        System.out.println();
        System.out.println("#################################################");
        int adminChoice=Main.kb3.nextInt();
        switch (adminChoice){
            case 1:
                Main.User1.createUser("y");
            case 2:
                Main.User1.deleteUser();
            case 3:
                Main.User1.welcomeMessage();
            default:
        }
    }

    //This is a int switch method for the base program that runs to other programs
    public void Program(String userKey)throws Exception{
        System.out.println("#################  OPTIONS MENU #################");
        System.out.println();
        System.out.println("PRESS 1 FOR : CHECK BALANCE");
        System.out.println("PRESS 2 FOR : WITHDRAW FUNDS");
        System.out.println("PRESS 3 FOR : TRANSFER FUNDS");
        System.out.println("PRESS 4 FOR : DELETE MY ACCOUNT");
        System.out.println("PRESS 5 FOR : EXIT TO LOGIN");
        System.out.println();
        System.out.println("#################################################");
        int chooseAdventure=Main.kb4.nextInt();
        switch (chooseAdventure) {
            case 1:
                checkBalance(userKey);
                break;
            case 2:
                withdrawFunds(userKey);
                break;
            case 3:
                transferFunds(userKey);
                break;
            case 4:
                Main.User1.DeleteThisUser(userKey);
                break;
            case 5:
                Main.User1.welcomeMessage();
                break;
            default:
                throw new Exception("you have chosen an invalid option");
        }
    }

    //This is the ending program to say goodbye and is used to close any loops
    public void cancel(){
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println();
        System.out.println("Thank you and please come again");
        System.out.println();
        System.out.println("#################################################");
        System.out.println("#################################################");
    }


}
