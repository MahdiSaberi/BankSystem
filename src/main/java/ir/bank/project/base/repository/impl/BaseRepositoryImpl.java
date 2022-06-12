package ir.bank.project.base.repository.impl;
import ir.bank.project.base.domain.BaseEntity;
import ir.bank.project.base.repository.BaseRepository;
import ir.bank.project.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<E,ID> {

    protected EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
    protected final Class<E> eClass;
    public abstract Class<E> getEClass();

    public BaseRepositoryImpl(EntityManager em) {
        this.em = em;
        this.eClass = getEClass();
    }

    @Override
    public E update(E e) {
        try {
            if(e.getId() == null){
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
            }

            else if(e.getId() != null){
                em.getTransaction().begin();
                em.merge(e);
                em.getTransaction().commit();
            }

        }catch (Exception exception){
            System.out.println("Error during merge Entity.");
            em.getTransaction().rollback();
        }
        return e;
    }

    @Override
    public void delete(E e) {
        try{
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }catch (Exception exception){
            System.out.println("Error during delete Entity.");
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteById(ID id) {
        try {
            em.createQuery("delete from " + eClass.getSimpleName() + " e where e.id=" + id, eClass).executeUpdate();
        }catch (Exception e){
            System.out.println("Entity not found!");
        }
    }

    @Override
    public E findById(ID id) {
        E e;
        try{
            e = em.find(eClass,id);
        }catch (Exception exception){
            e = null;
            System.out.println("Entity not found!");
        }
        return e;
    }

    @Override
    public List<E> findAll() {
        String quer = "select * from "+eClass.getSimpleName()+" e";
        TypedQuery<E> query = em.createQuery(quer,eClass);
        List<E> eList = query.getResultList();
        return eList;
    }
}