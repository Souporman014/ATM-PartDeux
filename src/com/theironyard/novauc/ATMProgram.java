package com.theironyard.novauc;

public class ATMProgram {

    int option;
    double withdraw;
    double quarters, dimes, nickles, pennies, remainder;
    int iQuarters,iDimes,iNickles,iPennies;

    public void setChange() {
        quarters = (int)(withdraw / .25);
        remainder = (withdraw % .25);
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
        iQuarters=(int)(quarters);
        iDimes=(int)dimes;
        iNickles=(int)nickles;
        iPennies=(int)pennies;
    }

    //This is a 
    public void checkBalance(String userKey) throws Exception {
        System.out.println("#####################  BALANCE MENU #####################");
        System.out.println("####                                                 ####");
        System.out.printf("Your current checking balance is    %.2f\n",
                Account.information.get(userKey).getCheckingBalance());
        System.out.printf("Your current savings balance is    %.2f\n",
                Account.information.get(userKey).getSavingBalance());
        System.out.println("####                                                 ####");
        int runLoop=1;
        while(runLoop==1) {
            System.out.println("would you like to return to the main menu: Y/N ");
            String balanceBack=Main.kb.nextLine();
            if (balanceBack.equalsIgnoreCase("y")) {
                Program(userKey);
            }
            else if (balanceBack.equalsIgnoreCase("n")) {
                cancel();
            }
        }
    }



    public void withdrawFunds(String userKey)throws Exception {
        System.out.println("################  WITHDRAW MENU #################");
        System.out.println();
        int runLoop = 1;
        while (runLoop == 1) {
            System.out.println("PLEASE ENTER THE AMOUNT YOU WISH TO WITHDRAW");
            double currentWithdraw = Main.kb.nextDouble();

            if (currentWithdraw> Account.information.get(userKey).getCheckingBalance()||currentWithdraw<=0){
                System.out.println("You have entered a larger amount than what is available");
                System.out.println("Or you have entered a number less than 0");
                withdrawFunds(userKey);
            } else {
                System.out.println("CURRENT BALANCE:    " + Account.information.get(userKey).getCheckingBalance());
                System.out.println("\tMINUS");
                System.out.println("WITHDRAWN AMOUNT:   " + currentWithdraw);
                System.out.println("WOULD YOU LIKE TO CONTINUE WITH THE WITHDRAW: Y/N");
                System.out.println();
                int runLoopAgain=1;
                while(runLoopAgain==1){
                    System.out.println("WOULD YOU LIKE TO CONTINUE WITH THE WITHDRAW: Y/N");
                    String response = Main.kb.nextLine();
                    if (response.equalsIgnoreCase("n")) {
                        Program(userKey);
                    } else if (response.equalsIgnoreCase("y")) {
                        setChange();
                        System.out.println("YOU WILL BE RECEIVING");
                        System.out.printf(iQuarters + " QUARTERS " + iDimes + " DIMES " + iNickles + " NICKLES " + iPennies + " PENNIES\n");
                        System.out.println("TRANSACTION HAS BEEN COMPLETED PLEASE TAKE YOUR MONEY");
                        double newAmount = Account.information.get(userKey).getCheckingBalance() - currentWithdraw;
                        Main.User1.currentUser(userKey).setCheckingBalance(newAmount);
                        System.out.println();
                        System.out.println("#################################################");
                        System.out.println();
                        System.out.printf("CURRENT AMOUNT IN ACCOUNT IS NOW %.2f \n", newAmount);
                        System.out.println();
                        int runLoopMore=1;
                        while(runLoopMore==1){
                            System.out.println("would you like to return to the main menu: Y/N ");
                            Main.kb.next();
                            String chooseMainMenu = Main.kb.nextLine();
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
                }
            }
        }
    }
    public void transferFunds(String userKey)throws Exception {
        System.out.println("###############     TRANSFER FUNDS MENU    ################");
        System.out.println();
        System.out.println("Please select which account you would like to transfer funds from");
        System.out.println();
        System.out.printf("Checking account balance %.2f\tSavings account balance %.2f\n",
                Account.information.get(userKey).getCheckingBalance(),
                Account.information.get(userKey).getSavingBalance());
        int runLoop=1;
        while (runLoop==1) {
            System.out.println("Enter checking or savings:");
            String whichAccount = Main.kb.nextLine();
            if (whichAccount.equalsIgnoreCase("checking")) {
                System.out.println();
                int runLoopAgain = 1;
                while (runLoopAgain == 1) {
                    System.out.println("Please enter who you would like to transfer funds to");
                    String transferPerson = Main.kb.nextLine();
                    if (Main.User1.information.get(transferPerson).getName().equalsIgnoreCase(transferPerson)) {
                        System.out.printf("Enter the funds value you would like to send to %s\n",
                                Main.User1.transferPerson(transferPerson).getName());
                        double transferFunds = Main.kb.nextDouble();
                        double newAmount = Main.User1.transferPerson(transferPerson).getCheckingBalance() + transferFunds;
                        Main.User1.transferPerson(transferPerson).setCheckingBalance(newAmount);
                    }
                    else{
                        System.out.println("The name you have entered does not have an account at this bank");
                        System.out.println("Please enter a new account name");
                    }
                }
            }else if (whichAccount.equalsIgnoreCase("savings")) {
                int runLoopAgain=1;
                while (runLoopAgain==1) {
                    System.out.println("Who please enter who you would like to transfer funds to");
                    String transferPerson = Main.kb.nextLine();
                    System.out.printf("Enter the funds value you would like to send to %s\n",
                            Main.User1.transferPerson(transferPerson).getName());
                    double transferFunds = Main.kb.nextDouble();
                    double newAmount = Main.User1.transferPerson(transferPerson).getSavingBalance() + transferFunds;
                    Main.User1.transferPerson(transferPerson).setSavingBalance(newAmount);
                }
            }


        }
    }
    public void cancel(){
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println();
        System.out.println("Thank you and please come again");
        System.out.println();
        System.out.println("#################################################");
        System.out.println("#################################################");
    }

    public void AdminProgram(String adminKey){
        System.out.println("############  ADMINISTRATOR MENU  ################");
        System.out.println();
        System.out.println("PRESS 1 FOR : CHECK BALANCE");
        System.out.println("PRESS 2 FOR : WITHDRAW FUNDS");
        System.out.println("PRESS 3 FOR : TRANSFER FUNDS");
        System.out.println("PRESS 4 FOR : CREATE ACCOUNT ");
        System.out.println("PRESS 5 FOR : DELETE ACCOUNT");
        System.out.println("PRESS 6 FOR : EXIT ATM");
        System.out.println();
        System.out.println("#################################################");
    }
    public void Program(String userKey)throws Exception{
        System.out.println("#################  OPTIONS MENU #################");
        System.out.println();
        System.out.println("PRESS 1 FOR : CHECK BALANCE");
        System.out.println("PRESS 2 FOR : WITHDRAW FUNDS");
        System.out.println("PRESS 3 FOR : TRANSFER FUNDS");
        System.out.println("PRESS 4 FOR : EXIT ATM");
        System.out.println();
        System.out.println("#################################################");
//        Main.kb.next();
        option=Main.kb.nextInt();
        switch (option) {
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
                cancel();
                break;
            default:
                throw new Exception("you have chosen an invalid option");
        }
    }

}
