package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Customer;
import ir.bank.domain.Record;
import ir.bank.repository.CustomerRepository;
import ir.bank.repository.RecordRepository;
import ir.bank.util.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class RecordRepositoryImpl extends BaseRepositoryImpl<Long, Record> implements RecordRepository {

    private EntityManagerFactory emf;

    public RecordRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Record> getClassType() {
        return Record.class;
    }

    @Override
    public void initRecords() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Record record = new Record();
        record.setMessage("Your Card is Blocked!");
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();

        em.getTransaction().begin();
        record = new Record();
        record.setMessage("Money were deposited");
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();

        em.getTransaction().begin();
        record = new Record();
        record.setMessage("Money was deducted from your account");
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();

    }
}
