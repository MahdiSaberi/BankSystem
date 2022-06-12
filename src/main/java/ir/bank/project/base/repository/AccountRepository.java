package ir.bank.project.base.repository;

import ir.bank.project.domain.Account;
import ir.bank.project.domain.BankBranch;
import ir.bank.project.domain.Card;
import ir.bank.project.domain.Customer;

public interface AccountRepository extends BaseRepository<Account,Long>{

    Customer getCustomer(Account account);

    Card getCard(Account account);

    BankBranch getBank(Account account);

}
