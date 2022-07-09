package ir.bank;

import ir.bank.domain.Customer;
import ir.bank.repository.impl.CustomerRepositoryImpl;
import ir.bank.service.CustomerService;
import ir.bank.service.dto.CustomerSearch;
import ir.bank.service.impl.CustomerServiceImpl;
import ir.bank.util.ApplicationContext;
import ir.bank.util.HibernateUtil;
import ir.bank.util.Menu;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
//        HibernateUtil.getEntityManagerFactory();
//        Menu menu = new Menu();
//        menu.firstMenu();

        //

        EntityManager entityManager = ApplicationContext.entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer>query = criteriaBuilder.createQuery(Customer.class);
        List lastNameList = Arrays.asList(new String[]{"Rezaei","Hassani"});

        Root<Customer> root = query.from(Customer.class);

        Expression<String> exp = root.get("lastName");

        Predicate in = exp.in(lastNameList);

        query.where(in);
        query.groupBy(root.get("lastName"));
        CriteriaQuery<Customer> select = query.select(root);
        TypedQuery<Customer> query1 = entityManager.createQuery(select);
        System.out.println(query1.getResultList());
        entityManager.close();
//        TypedQuery<Customer> typedQuery = entityManager.createQuery("from Customer ",Customer.class);
//
//        EntityGraph<Customer> entityGraph = entityManager.createEntityGraph(Customer.class);
//
//        entityGraph.addAttributeNodes("firstName","lastName");
//
//        typedQuery.setHint("javax.persistence.fetchgraph",entityGraph);
//
//        List<Customer> customerList = typedQuery.getResultList();
//
//        for(Customer c : customerList)
//        {
//            System.out.println(c.getFirstName());
//            System.out.println(c.getLastName());
//            System.out.println(c.getId());
//        }

        //
//        CustomerService customerService = ApplicationContext.getCustomerService();
//        CustomerSearch customerSearch = new CustomerSearch();
//
//        customerSearch.setFirstName("i");
//        customerSearch.setLastName("i");
//        List<Customer> customerList = customerService.findAllByCriteria(customerSearch);
//
//        if(customerList != null)
//            System.out.println(customerList);
    }
}
