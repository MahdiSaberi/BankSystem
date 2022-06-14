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
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;

        TypedQuery<Account> query = emf.createEntityManager().createQuery(
                "from Account ", Account.class);
        List<Account> accounts = query.getResultList();

        for (Account a : accounts){
            System.out.println("====================");
            System.out.println("Bank: "+a.getBank().getName()+"\nCard number: "+a.getCard().getCardNumber()
            +"\nOwner: "+a.getCustomer().getFirstName()+" "+a.getCustomer().getLastName());
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

    public static void createBank(){
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        System.out.println("====================");
        System.out.println("Enter Bank Name:");
        String name = context.getStringScanner().next();
        System.out.println("Enter Boss Name:");
        String bossName = context.getStringScanner().next();

        try{
            em.getTransaction().begin();
            Bank bank = new Bank();
            bank.setName(name);
            bank.setBoss(bossName);
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
        Long origin = context.getIntScanner().nextLong();
        Integer password = 0;

        Card card = ApplicationContext.cardRepository.findByCardNumber(origin);
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
            if (password == card.getPassword())
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

        Records record = new Records();
        record.setMessage("Charged: "+card.getCardNumber() + "\tAmount: " + money);
        record.setCard(card);
        record.setDate(new Date());
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();
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

            if(destCard.getCardNumber() != destination)
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

                    String message = "Deposit:\n"+"Origin: "+oriCard.getCardNumber() + "\nAmount: " + money+"\nDestination: "+destCard.getCardNumber();

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

    }

    public static void inquiry(){
        Menu menu = new Menu();
        ApplicationContext context = new ApplicationContext();
        ApplicationContext.reloadAll();
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        EntityManager em = emf.createEntityManager();

        System.out.println("====================");
        System.out.println("Enter Card Number:");
        Long cardNumber = context.getIntScanner().nextLong();

        Card card = ApplicationContext.cardRepository.findByCardNumber(cardNumber);
        TypedQuery<Records> query = emf.createEntityManager().createQuery(
                "from Records ", Records.class);
        List<Records> records = query.getResultList();

        for(Records r: records){
            if(r.getCard().getId() == card.getId())
                System.out.println(r.getMessage()+"\nDate: "+r.getDate());
        }
    }
}