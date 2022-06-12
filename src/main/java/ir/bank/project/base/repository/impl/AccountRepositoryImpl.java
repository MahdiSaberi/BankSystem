package ir.bank.project.base.repository.impl;

import ir.bank.project.base.repository.AccountRepository;
import ir.bank.project.domain.Account;
import ir.bank.project.domain.Bank;
import ir.bank.project.domain.Card;
import ir.bank.project.domain.Customer;

import java.util.List;
import java.util.Set;

public abstract class AccountRepositoryImpl implements AccountRepository {

    @Override
    public Set<Account> customerAccounts(Customer customer){
                return customer.getAccounts();
    }

}
