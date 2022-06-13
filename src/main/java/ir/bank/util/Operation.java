package ir.bank.util;

import ir.bank.domain.Account;
import ir.bank.domain.Bank;
import ir.bank.domain.Card;
import ir.bank.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.ListIterator;

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
            card.setCardNumber(ApplicationContext.cardRepository.generateNumber());
            System.out.println("Your Card Number is: "+card.getCardNumber());
            System.out.println("Select your password:");
            Integer password = context.getIntScanner().nextInt();
            card.setPassword(password);
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
        EntityManager em = emf.createEntityManager();

        Integer password = context.getIntScanner().nextInt();

        System.out.println("Enter Origin Card Number:");
        Long originCard = context.getIntScanner().nextLong();
        Card card = new Card();

        try {
            card = ApplicationContext.cardRepository.findByCardNumber(originCard);
        }
        catch (NullPointerException e){
            System.out.println("This card was not found!");
        }

        boolean matched = false;
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter Password:");
            password = context.getIntScanner().nextInt();

            if(password == card.getPassword()){
                matched = true;
                break;
            }
            System.out.println("Password does not match!");
            System.out.println("Tries: "+i+1+"/3");
            if(i+1 == 3){

            }
        }


    }
}