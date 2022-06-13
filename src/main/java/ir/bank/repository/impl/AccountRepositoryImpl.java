package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Account;
import ir.bank.domain.Card;
import ir.bank.repository.AccountRepository;
import ir.bank.repository.CardRepository;

import javax.persistence.EntityManagerFactory;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Long, Account> implements AccountRepository {
    private EntityManagerFactory emf;

    public AccountRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }

    @Override
    public Class<Account> getClassType() {
        return null;
    }
}
