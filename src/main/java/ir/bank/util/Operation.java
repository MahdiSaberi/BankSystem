package ir.bank.util;

import ir.bank.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.*;

public class Operation {

    public static void addCustomer(){
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        ApplicationContext context = new ApplicationContext();

        System.out.println("Firstname:");
        String firstName = context.getStringScanner().next();

        System.out.println("Lastname:");
        String lastName = context.getStringScanner().next();

        try {
            em.getTransaction().begin();
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            ApplicationContext.customerRepository.save(customer);
            em.getTransaction().commit();
            System.out.println("Customer Successfully Created!");
            System.out.println("Select Bank for create Account:");
            TypedQuery<Bank> query = emf.createEntityManager().createQuery("select new Bank(name,id) from Bank", Bank.class);
            List<Bank> banks = query.getResultList();
            for (Bank b : banks)
                System.out.println(b.getId()+". "+b.getName());
            Long bankId = context.getIntScanner().nextLong();
            Bank bank = ApplicationContext.bankRepository.findById(bankId);
            accountCreation(customer,bank);
        }
        catch (Exception e){
            System.out.println("There was an error during create Customer.");
        }

    }

    public static void customerList(){
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;

        TypedQuery<Customer> query = emf.createEntityManager().createQuery(
                "from Customer", Customer.class);
        List<Customer> customers = query.getResultList();

        for (Customer c : customers){
            System.out.println("====================");
            System.out.println(c.getId()+". "+c.getFirstName()+" "+c.getLastName());
        }
        System.out.println("====================");
    }

    public static void accountList(){
        ApplicationContext.reloadAll();
        ApplicationContext context = new ApplicationContext();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;

        customerList();
        System.out.println("Select Customer by ID:");
        Long customerId = context.getIntScanner().nextLong();
        Customer customer = ApplicationContext.customerRepository.findById(customerId);

        TypedQuery<Account> query = emf.createEntityManager().createQuery(
                "from Account ", Account.class);
        List<Account> accounts = query.getResultList();

        for (Account a : accounts){

            if(a.getCustomer().getId().longValue() == customerId) {
                System.out.println("====================");
                System.out.println("Bank: " + a.getBank().getName() + "\tCard number: " + a.getCard().getCardNumber()+
                        "\tPassword: "+a.getCard().getPassword()+"\tExpire Date: "+a.getCard().getDate()+"\tBalance: "+a.getCard().getMoney());
            }
        }
        System.out.println("====================");
    }

    public static void createAccount(){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;

        System.out.println("====================");
        System.out.println("To create Account, Select Customer by ID:");
        customerList();
        Long customerId = context.getIntScanner().nextLong();

        Customer customer = ApplicationContext.customerRepository.findById(customerId);
        System.out.println("====================");
        System.out.println("Now, Select Bank by ID:");
        bankList();

        Long bankId = context.getIntScanner().nextLong();
        Bank bank = ApplicationContext.bankRepository.findById(bankId);

        accountCreation(customer,bank);
    }

    public static void bankList(){
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;

        TypedQuery<Bank> query = emf.createEntityManager().createQuery(
                "select new Bank (name,id) from Bank ", Bank.class);
        List<Bank> banks = query.getResultList();

        for (Bank b : banks){
            System.out.println("====================");
            System.out.println(b.getId()+". "+b.getName());
        }
        System.out.println("====================");
    }

    public static void accountCreation(Customer customer,Bank bank){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Account account = new Account();
            account.setCustomer(customer);
            account.setBank(bank);

            Card card = new Card();
            card.setDate(LocalDate.of(2023,06,14));
            card.setCardNumber(ApplicationContext.cardRepository.generateNumber());
            System.out.println("Your Card Number is: "+card.getCardNumber());
            System.out.println("Select your password:");
            Integer password = context.getIntScanner().nextInt();
            card.setPassword(password);
            card.setMoney(0L);
            account.setCard(card);
            ApplicationContext.cardRepository.save(card);
            ApplicationContext.accountRepository.save(account);
            em.getTransaction().commit();
            System.out.println("Account Successfully Created!");
        }catch (Exception e){
            System.out.println("There was an error during Account creation");
            em.getTransaction().rollback();
        }

    }
    public static Employee addEmployee(){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();
        Employee employee = new Employee();

        System.out.println("Enter Firstname:");
        String fName = context.getStringScanner().next();
        System.out.println("Enter Lastname:");
        String lName = context.getStringScanner().next();
        System.out.println("Enter Age:");
        Integer age = context.getIntScanner().nextInt();
        System.out.println("Select Bank by ID:");
        bankList();

        Long bankId = context.getIntScanner().nextLong();
        Bank bank = ApplicationContext.bankRepository.findById(bankId);

        System.out.println("Select Employee Degree:\n1.Ordinary\n2.Boss");
        int selectDegree = 0;

        try{
            selectDegree = context.getIntScanner().nextInt();
            if(selectDegree == 1)
                employee.setBoss(false);
            else if(selectDegree == 2)
                employee.setBoss(true);

        }catch (Exception e){
            if(selectDegree > 2)
            {
                System.out.println("Try again!");
                Operation.addEmployee();
            }
        }

        try {
            em.getTransaction().begin();
            employee.setFirstName(fName);
            employee.setLastName(lName);
            employee.setAge(age);
            employee.setBank(bank);
            em.persist(employee);
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println("Wrong Values!");
            em.getTransaction().rollback();
        }

        return employee;
    }

    public static void employeeList(){
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;

        TypedQuery<Employee> query = emf.createEntityManager().createQuery(
                "from Employee ", Employee.class);
        List<Employee> employees = query.getResultList();

        for (Employee e : employees){
            System.out.println("====================");
            System.out.print("ID:"+e.getId()+" FullName: "+e.getFirstName()+" "+e.getLastName());
            System.out.print("\tBank: "+e.getBank().getName());
            System.out.print("\tDegree: ");
            if(e.isBoss()) System.out.println("Boss");
            else System.out.println("Ordinary");
        }
        System.out.println("====================");

    }

    public static void removeEmployee(){
        ApplicationContext context = new ApplicationContext();
        System.out.println("====================");
        System.out.println("Select Employee by ID:");
        employeeList();

        Long empId = context.getIntScanner().nextLong();
        Employee employee = ApplicationContext.employeeRepository.findById(empId);

        ApplicationContext.employeeRepository.delete(employee);
        System.out.println("====================");
        System.out.println("Employee Removed!");
    }

    public static void createBank(){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        System.out.println("====================");
        System.out.println("Enter Bank Name:");
        String name = context.getStringScanner().next();
//        System.out.println("Add Employees:");
//        Employee employee = addEmployee();

        try{
            em.getTransaction().begin();
            Bank bank = new Bank();
            bank.setName(name);
//            bank.setBoss(bossName);
            ApplicationContext.bankRepository.save(bank);
            em.getTransaction().commit();
            System.out.println("Bank Successfully Added!");
        }catch (Exception e){
            System.out.println("You can't Add this Bank.");
            em.getTransaction().rollback();
        }

    }

    public void transactions(){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        Menu menu = new Menu();
        EntityManager em = emf.createEntityManager();
        Integer password = 0;

        System.out.println("Enter Origin Card Number:");
        Long originCard = context.getIntScanner().nextLong();
        Card card = new Card();

        try {
            card = ApplicationContext.cardRepository.findByCardNumber(originCard);
        }
        catch (NullPointerException e){
            System.out.println("Card was not found!");
            menu.firstMenu();
        }

    }

    public static Card cardInfo(){
        ApplicationContext.reloadAll();
        ApplicationContext context = new ApplicationContext();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        System.out.println("====================");
        System.out.println("Card Number:");

        Long origin = null;
        Integer password = 0;

        origin = context.getIntScanner().nextLong();
        Card card = null;
        try {
            card = ApplicationContext.cardRepository.findByCardNumber(origin);
        }catch (Exception e){
            System.out.println("====================");
            System.out.println("Card not found!");
            ApplicationContext.menu.transactionMenu();
        }

        List<Records> records = ApplicationContext.recordRepository.findByCardId(card.getId());

        if(records != null) {
            for (Records r : records) {

                if (r.getMessage().equals("Your card is blocked!") &&
                        r.getCard().getCardNumber().longValue() == card.getCardNumber().longValue()) {
                    System.out.println("Your card is blocked!");
                    return null;
                }
            }
        }

        System.out.println("Password:");
        for (int i = 0; i < 3; i++) {
            password = context.getIntScanner().nextInt();
            if (password.intValue() == card.getPassword().intValue())
                return card;

            System.out.println("Wrong password!");
        }
        em.getTransaction().begin();
        Records record = new Records();
        record.setMessage("Your card is blocked!");
        record.setCard(card);
        record.setDate(new Date());
        ApplicationContext.recordRepository.save(record);
        System.out.println("Done");
        em.getTransaction().commit();
        return null;
    }

    public static void changePassword(){
        ApplicationContext context = new ApplicationContext();
        Menu menu = new Menu();
        Card card = cardInfo();
        System.out.println("====================");
        System.out.println("Enter new Password:");
        Integer password = context.getIntScanner().nextInt();
        Integer oldPassword = card.getPassword();
        card.setPassword(password);
        card = ApplicationContext.cardRepository.update(card);
        Records records = new Records();
        records.setMessage("Password Changed from "+oldPassword+" to "+password);
        records.setDate(new Date());
        records.setCard(card);
        ApplicationContext.recordRepository.save(records);
        System.out.println("Done");
        menu.firstMenu();
    }

    public static void withdrawal(){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        Card card = cardInfo();

        if (card == null){
            Menu menu = new Menu();
            menu.firstMenu();
        }
        Long money = 0L;

        while(true){
            System.out.println("====================");
            System.out.println("Your Amount: "+card.getMoney());
            System.out.println("Enter Money Value for Withdrawal:");
            money = context.getIntScanner().nextLong();

            if(money > card.getMoney())
                System.out.println("Your money is not enough for Withdrawal.");

            else{
                em.getTransaction().begin();
                Records record = new Records();
                record.setMessage("Transaction (Withdrawal): "+card.getMoney() + "-" +money);
                record.setCard(card);
                record.setDate(new Date());
                ApplicationContext.recordRepository.save(record);
                em.getTransaction().commit();

                em.getTransaction().begin();
                card.setMoney(card.getMoney()-money);
                ApplicationContext.cardRepository.update(card);
                em.getTransaction().commit();
                System.out.println("Done!");

                break;
            }
        }

    }

    public static void charge(){
        Menu menu = new Menu();
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();
        Card card = cardInfo();

        if (card == null)
            menu.firstMenu();

        System.out.println("Enter Amount:");
        Long money = context.getIntScanner().nextLong();
        em.getTransaction().begin();
        card.setMoney(card.getMoney()+money);
        ApplicationContext.cardRepository.update(card);
        em.getTransaction().commit();
        System.out.println("Your Account was Charged!");

        em.getTransaction().begin();
        Records record = new Records();
        record.setMessage("Charged: "+card.getCardNumber() + "\tAmount: " + money);
        record.setCard(card);
        record.setDate(new Date());
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();
        menu.transactionMenu();
    }

    public static void deposit(){
        Menu menu = new Menu();
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();
        Card oriCard = cardInfo();

        if (oriCard == null)
            menu.firstMenu();

        while (true){
            System.out.println("====================");
            System.out.println("Enter Destination Card Number:");
            Long destination = context.getIntScanner().nextLong();
            Card destCard = ApplicationContext.cardRepository.findByCardNumber(destination);

            if(destCard.getCardNumber().longValue() != destination)
                System.out.println("Destination Card not found!");

            else{
                System.out.println("Enter Money to Deposit:");
                Long money = context.getIntScanner().nextLong();

                if(money > oriCard.getMoney()){
                    System.out.println("You don't have enough Money!");
                    menu.transactionMenu();
                }


                else{
                    em.getTransaction().begin();
                    oriCard.setMoney(oriCard.getMoney()-money);
                    destCard.setMoney(destCard.getMoney()+money);
                    ApplicationContext.cardRepository.update(oriCard);
                    ApplicationContext.cardRepository.update(destCard);
                    em.getTransaction().commit();
                    System.out.println("Done!");
                    System.out.println("====================");
                    String message = "Deposit:\t"+"Origin: "+oriCard.getCardNumber() + "\tAmount: " + money+"\tDestination: "+destCard.getCardNumber();

                    em.getTransaction().begin();
                    Records record = new Records();
                    record.setMessage(message);
                    record.setCard(oriCard);
                    record.setDate(new Date());
                    ApplicationContext.recordRepository.save(record);

                    Records record1 = new Records();
                    record1.setMessage(message);
                    record1.setCard(destCard);
                    record1.setDate(new Date());
                    ApplicationContext.recordRepository.save(record1);
                    em.getTransaction().commit();
                    break;
                }

            }
        }
    menu.transactionMenu();
    }

    public static void inquiry(){
        Menu menu = new Menu();
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        System.out.println("====================");
        System.out.println("Enter Card Number:");

        Long cardNumber = null;
        Card card = null;
        List<Records> records = null;

        try {
            cardNumber = context.getIntScanner().nextLong();

        card = ApplicationContext.cardRepository.findByCardNumber(cardNumber);
        TypedQuery<Records> query = emf.createEntityManager().createQuery(
                "from Records ", Records.class);
        records = query.getResultList();
        }catch (Exception e){
            System.out.println("====================");
            System.out.println("Something went Wrong!");
            menu.transactionMenu();
        }

        for(Records r: records){
            if(r.getCard().getId() == card.getId()){
                System.out.println("====================");
                System.out.println(r.getMessage()+"\tDate: "+r.getDate());
            }
            else {
                System.out.println("====================");
                System.out.println("No Records!");
                break;
            }

        }
        menu.transactionMenu();
    }
}