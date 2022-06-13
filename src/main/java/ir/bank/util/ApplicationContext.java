package ir.bank.util;

import ir.bank.repository.*;
import ir.bank.repository.impl.*;

import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class ApplicationContext {

    private Scanner intScanner = null;
    private Scanner stringScanner = null;

    public static final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
    public static CustomerRepository customerRepository;
    public static BankRepository bankRepository;
    public static AccountRepository accountRepository;
    public static CardRepository cardRepository;
    public static RecordRepository recordRepository;


    public static void reloadAll() {
        customerRepository = new CustomerRepositoryImpl(entityManagerFactory);
        bankRepository = new BankRepositoryImpl(entityManagerFactory);
        accountRepository = new AccountRepositoryImpl(entityManagerFactory);
        cardRepository = new CardRepositoryImpl(entityManagerFactory);
        recordRepository = new RecordRepositoryImpl(entityManagerFactory);
    }

    public Scanner getIntScanner() {
        if (intScanner == null)
            this.intScanner = new Scanner(System.in);

        return intScanner;
    }

    public Scanner getStringScanner() {
        if (stringScanner == null)
            this.stringScanner = new Scanner(System.in);

        return stringScanner;
    }
}

