package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Account;
import ir.bank.domain.Customer;
import ir.bank.repository.CustomerRepository;
import ir.bank.service.dto.CustomerSearch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Long, Customer> implements CustomerRepository {

    //private EntityManagerFactory emf;

    public CustomerRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Customer> getClassType() {
        return Customer.class;
    }


    public Customer findByFirstName(String firstName) {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Customer where firstName=:firstName",Customer.class).setParameter
                ("firstName",firstName).getSingleResult();
    }

    public Customer findByAccountId(Long id){
        EntityManager em = emf.createEntityManager();

        return em.find(Account.class,id).getCustomer();
    }

    @Override
    public List<Customer> findAllByCriteria(CustomerSearch customerSearch) {
        EntityManager entityManager = emf.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();

        setFirstNamePredicate(predicates, root, criteriaBuilder, customerSearch.getFirstName());
        setLastNamePredicate(predicates, root, criteriaBuilder, customerSearch.getLastName());
        //isActivePredicate(predicates, root, criteriaBuilder, customerSearch.getActive());
        //setUsernamePredicate(predicates, root, criteriaBuilder, customerSearch.getUsername());

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        //criteriaQuery.where(criteriaBuilder.equal(root.get("firstName"),"Saeid"));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private void setUsernamePredicate(List<Predicate> predicates, Root<Customer> root, CriteriaBuilder criteriaBuilder, String username) {
        if(username != null)
            predicates.add(criteriaBuilder.like(root.get("username"),"%" + username + "%"));
    }

    private void isActivePredicate(List<Predicate> predicates, Root<Customer> root, CriteriaBuilder criteriaBuilder, Boolean active) {
        if(active != null){
            if(active)
                predicates.add(criteriaBuilder.isTrue(root.get("isActive")));

            else
                predicates.add(criteriaBuilder.or(criteriaBuilder.isFalse(root.get("isActive")),criteriaBuilder.isNull(root.get("isActive"))));

        }
    }

    private void setLastNamePredicate(List<Predicate> predicates, Root<Customer> root, CriteriaBuilder criteriaBuilder, String lastName) {
        if(lastName != null)
            predicates.add(criteriaBuilder.like(root.get("lastName"),"%" + lastName + "%"));
    }

    private void setFirstNamePredicate(List<Predicate> predicates, Root<Customer> root, CriteriaBuilder criteriaBuilder, String firstName) {
            if(firstName != null){
                //where  field   operator    value

                predicates.add(criteriaBuilder.like(root.get("firstName"),"%" + firstName + "%"));
                //Path<String> field = root.get("firstName");
                //Predicate predicate = criteriaBuilder.like(field,"%"+ firstName + "%");
                //predicates.add(predicate);
            }

    }


}
