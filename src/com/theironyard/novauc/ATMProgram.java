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
        iQuarters=(int)quarters;
        iDimes=(int)dimes;
        iNickles=(int)nickles;
        iPennies=(int)pennies;
    }

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
            String options=Main.kb.nextLine();
            if (options.equalsIgnoreCase("y")) {
                Program(userKey);
            } else if (options.equalsIgnoreCase("n")) {
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
            double currentwithdraw = Main.kb.nextDouble();

            if (currentwithdraw> Account.information.get(userKey).getCheckingBalance()) {
                System.out.println("You have entered a larger amount than what is available");
                withdrawFunds(userKey);
            } else {
                System.out.println("CURRENT BALANCE:    " + Account.information.get(userKey).getCheckingBalance());
                System.out.println("\tMINUS");
                System.out.println("WITHDRAWN AMOUNT:   " + withdraw);
                System.out.println("WOULD YOU LIKE TO CONTINUE WITH THE WITHDRAW: Y/N");
                System.out.println();
                Main.kb.next();
                String response = Main.kb.nextLine();
                if (response.equalsIgnoreCase("n")) {
                    Program(userKey);
                } else if (response.equalsIgnoreCase("y")) {
                    setChange();
                    System.out.println("YOU WILL BE RECEIVING");
                    System.out.printf(iQuarters + " QUARTERS " + iDimes + " DIMES " + iNickles + " NICKLES " + iPennies + " PENNIES\n");
                    System.out.println("TRANSACTION HAS BEEN COMPLETED PLEASE TAKE YOUR MONEY");
                    double newAmount = Account.information.get(userKey).getCheckingBalance() - withdraw;
                    Main.User1.currentUser(userKey).setCheckingBalance(newAmount);
                    System.out.println();
                    System.out.println("#################################################");
                    System.out.println();
                    System.out.printf("CURRENT AMOUNT IN ACCOUNT IS NOW %.2f \n", newAmount);
                    System.out.println();
                    System.out.println("would you like to return to the main menu: Y/N ");
                    Main.kb.next();
                    String options = Main.kb.nextLine();
                    switch (options) {
                        case "y":
                            Program(userKey);
                            break;
                        case "n":
                            cancel();
                            break;
                        default:
                            throw new Exception("you have chosen an invalid option");
                    }
                }
            }
        }
    }
    public void transferFunds(String userKey)throws Exception{
        System.out.println("###############     TRANSFER FUNDS MENU    ################");
        System.out.println();
        System.out.println("Please select which account you would like to transfer funds from");
        System.out.println();
        System.out.printf("Checking account balance %.2f\tSavings account balance %.2f\n",
                Account.information.get(userKey).getCheckingBalance(),
                Account.information.get(userKey).getSavingBalance());
        System.out.println("Enter checking or savings:");
        Main.kb.next();
        String whichAccount = Main.kb.nextLine();
        if(whichAccount.equalsIgnoreCase("checking")){
            System.out.println();
            System.out.println("Please enter who you would like to transfer funds to");
            Main.kb.next();
            String transferPerson = Main.kb.nextLine();
            System.out.printf("Enter the funds value you would like to send to %s\n",
                    Main.User1.transferPerson(transferPerson).getName());
            Main.kb.next();
            double transferFunds=Main.kb.nextDouble();
            double newAmount=Main.User1.transferPerson(transferPerson).getCheckingBalance()+transferFunds;
            Main.User1.transferPerson(transferPerson).setCheckingBalance(newAmount);
        }
        else if (whichAccount.equalsIgnoreCase("savings")){

            System.out.println();
            System.out.println("Who please enter who you would like to transfer funds to");
            Main.kb.next();
            String transferPerson = Main.kb.nextLine();
            System.out.printf("Enter the funds value you would like to send to %s\n",
                    Main.User1.transferPerson(transferPerson).getName());
            Main.kb.next();
            double transferFunds=Main.kb.nextDouble();
            double newAmount=Main.User1.transferPerson(transferPerson).getSavingBalance()+transferFunds;
            Main.User1.transferPerson(transferPerson).setSavingBalance(newAmount);
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
        System.out.println("PRESS 5 FOR : EXIT ATM");
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
