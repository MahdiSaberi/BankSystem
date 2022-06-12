package ir.bank.project;

import ir.bank.project.util.HibernateUtil;

public class Application {
    public static void main(String[] args) {
        HibernateUtil.getEntityManagerFactory().createEntityManager();
        System.out.println("Done!");
    }
}
