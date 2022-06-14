package ir.bank.util;

public class Menu {
    private ApplicationContext context = new ApplicationContext();

    public void firstMenu(){
        System.out.println("BANK SYSTEM");
        System.out.println("====================");
        System.out.println("1.Customer\n2.Account\n3.Banks");
        System.out.println("====================");
        System.out.println("4.Transaction\n5.Exit");
        System.out.println("====================");
        int select = context.getIntScanner().nextInt();

        switch (select){
            case 1:
                customerMenu();
                break;
            case 2:
                accountMenu();
                break;
            case 3:
                banksMenu();
                break;
            case 4:
                transactionMenu();
                break;
            case 5:
                System.exit(0);
        }
    }
    //First Menu
    public void customerMenu(){
        System.out.println("====================");
        System.out.println("1.Customers List\n2.Add Customer\n3.Back");
        System.out.println("====================");
        int select = context.getIntScanner().nextInt();

        switch (select){
            case 1:
                Operation.customerList();
                firstMenu();
                break;
            case 2:
                Operation.addCustomer();
                firstMenu();
                break;
            case 3:
                firstMenu();
        }

    }

    public void accountMenu(){
        System.out.println("====================");
        System.out.println("1.Accounts List\n2.Create Account\n3.Back");
        System.out.println("====================");

        int select = context.getIntScanner().nextInt();
        switch (select){
            case 1:
                Operation.accountList();
                firstMenu();
                break;
            case 2:
                Operation.createAccount();
                firstMenu();
                break;
            case 3:
                firstMenu();
                break;
        }
    }

    public void banksMenu(){
        System.out.println("====================");
        System.out.println("1.Banks List\n2.Create Bank\n3.Back");
        System.out.println("====================");

        int select = context.getIntScanner().nextInt();
        switch (select){
            case 1:
                Operation.bankList();
                firstMenu();
                break;
            case 2:
                Operation.createBank();
                firstMenu();
                break;
            case 3:
                firstMenu();
                break;
        }
    }

    public void transactionMenu(){
        System.out.println("====================");
        System.out.println("1.Withdrawal(برداشت)\n2.Charge(شارژ)\n3.Deposit(واریز)\n4.Inquiry(استعلام)\n5.Back");
        System.out.println("====================");

        int select = context.getIntScanner().nextInt();
        switch (select){
            case 1:
                Operation.withdrawal();
                firstMenu();
                break;
            case 2:
                Operation.charge();
                firstMenu();
                break;
            case 3:
                Operation.deposit();
                firstMenu();
                break;
            case 4:
                Operation.inquiry();
                break;
            case 5:
                firstMenu();
                break;
        }
    }
    //End First Menu

}
