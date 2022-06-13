package ir.bank.repository;

import ir.bank.base.repository.BaseRepository;
import ir.bank.domain.Bank;

public interface BankRepository extends BaseRepository<Long, Bank> {

    Bank findByName(String name);
}
