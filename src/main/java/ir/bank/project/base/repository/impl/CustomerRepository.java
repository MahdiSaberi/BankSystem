package ir.bank.project.base.repository.impl;

import ir.bank.project.domain.Customer;
import ir.bank.project.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public abstract class CustomerRepository implements ir.bank.project.base.repository.CustomerRepository {
    @Override
    public Customer findByName(String fullname) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where fullName=:fullName",Customer.class);
        Customer customer = query.setParameter("fullName",fullname).getSingleResult();
        return customer;
    }
}
