package ir.bank.project.base.repository;

import ir.bank.project.domain.Account;
import ir.bank.project.domain.Bank;
import ir.bank.project.domain.Card;
import ir.bank.project.domain.Customer;

import java.util.Set;

public interface AccountRepository extends BaseRepository<Account,Long>{

    Set<Account> customerAccounts(Customer customer);
}
