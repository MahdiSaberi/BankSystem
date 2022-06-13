package ir.bank;

import ir.bank.base.repository.BaseRepository;
import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Account;
import ir.bank.domain.Bank;
import ir.bank.domain.Card;
import ir.bank.domain.Customer;
import ir.bank.repository.BankRepository;
import ir.bank.repository.impl.BankRepositoryImpl;
import ir.bank.repository.impl.CustomerRepositoryImpl;
import ir.bank.util.ApplicationContext;
import ir.bank.util.Menu;
import ir.bank.util.Operation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.firstMenu();
    }

}
