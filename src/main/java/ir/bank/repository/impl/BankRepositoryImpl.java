package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Bank;
import ir.bank.domain.Customer;
import ir.bank.repository.BankRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class BankRepositoryImpl extends BaseRepositoryImpl<Long, Bank> implements BankRepository {

    private EntityManagerFactory emf;

    public BankRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }

    @Override
    public Class<Bank> getClassType() {
        return Bank.class;
    }

    @Override
    public Bank findByName(String name) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Bank where name=:name", Bank.class);
        query.setParameter("name",name);
        Bank bank = (Bank) query.getSingleResult();
        return bank;
    }
}
