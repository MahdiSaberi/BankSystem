package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Customer;
import ir.bank.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Long, Customer> implements CustomerRepository {

    private EntityManagerFactory emf;

    public CustomerRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }

    @Override
    public Class<Customer> getClassType() {
        return Customer.class;
    }


    public Customer findByFirstName(String firstName) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Customer where firstName=:firstName", Customer.class);
        query.setParameter("firstName",firstName);
        Customer customer = (Customer) query.getSingleResult();
        return customer;
    }

    public Customer findByAccountId(Long id){
        Customer customer = new Customer();
        return null;
    }


}
