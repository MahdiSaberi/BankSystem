package ir.bank.repository;

import ir.bank.base.repository.BaseRepository;
import ir.bank.domain.Customer;

public interface CustomerRepository extends BaseRepository<Long, Customer> {

    Customer findByFirstName(String username);
}
