package ir.bank.project.base.repository.impl;

import ir.bank.project.base.repository.AccountRepository;
import ir.bank.project.domain.Account;
import ir.bank.project.domain.Bank;
import ir.bank.project.domain.Card;
import ir.bank.project.domain.Customer;

public abstract class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Customer getCustomer(Account account) {
        //Long id = account.getId();
        return null;
    }

    @Override
    public Card getCard(Account account) {
        return null;
    }

    @Override
    public Bank getBank(Account account) {
        return null;
    }
}
