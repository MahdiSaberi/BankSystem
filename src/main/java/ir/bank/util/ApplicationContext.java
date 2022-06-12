package ir.bank.util;

import ir.bank.repository.CustomerRepository;
import ir.bank.repository.impl.CustomerRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class ApplicationContext {

    public static final EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
    public static CustomerRepository customerRepository;


    public static void reloadAll() {
        customerRepository = new CustomerRepositoryImpl(entityManagerFactory);
    }
}

