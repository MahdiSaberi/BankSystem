package ir.bank.project.base.repository;

import ir.bank.project.domain.Customer;

public interface CustomerRepository extends BaseRepository<Customer, Long>{

    Customer findByName(String fullname);
}
