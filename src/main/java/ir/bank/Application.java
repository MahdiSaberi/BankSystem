package ir.bank;

import ir.bank.domain.Customer;
import ir.bank.repository.impl.CustomerRepositoryImpl;
import ir.bank.util.ApplicationContext;

import javax.persistence.EntityManagerFactory;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = ApplicationContext.entityManagerFactory;
        System.out.println("Done");
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(emf);
        Customer customer = customerRepository.findByFirstName("Hassan");
        System.out.println(customer.getLastName());
    }
}
