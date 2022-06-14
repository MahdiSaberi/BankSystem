package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Card;
import ir.bank.domain.Records;
import ir.bank.repository.RecordRepository;
import ir.bank.util.ApplicationContext;
import ir.bank.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class RecordRepositoryImpl extends BaseRepositoryImpl<Long, Records> implements RecordRepository {

    private EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();

    public RecordRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Records> getClassType() {
        return Records.class;
    }

    @Override
    public void initRecords() {
        EntityManager em = emf.createEntityManager();
        ApplicationContext.reloadAll();
        em.getTransaction().begin();
        Records record = new Records();
        record.setMessage("Your Card is Blocked!");
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();

        em.getTransaction().begin();
        record = new Records();
        record.setMessage("Money were deposited");
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();

        em.getTransaction().begin();
        record = new Records();
        record.setMessage("Money was deducted");
        ApplicationContext.recordRepository.save(record);
        em.getTransaction().commit();

    }

    @Override
    public List<Records> findByCardId(Long id) {
        ApplicationContext.reloadAll();
        TypedQuery<Records> query = emf.createEntityManager().createQuery("from Records ",Records.class);
        List<Records> records = query.getResultList();

        return records;
    }
}
