package ir.bank.project.base.repository;

import ir.bank.project.domain.Account;
import ir.bank.project.domain.Bank;
import ir.bank.project.domain.Card;
import ir.bank.project.domain.Customer;

public interface AccountRepository extends BaseRepository<Account,Long>{

    Customer getCustomer(Account account);

    Card getCard(Account account);

    Bank getBank(Account account);

}
