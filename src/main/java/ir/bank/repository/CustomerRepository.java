package ir.bank.repository;

import ir.bank.base.repository.BaseRepository;
import ir.bank.domain.Customer;
import ir.bank.service.dto.CustomerSearch;

import java.util.List;

public interface CustomerRepository extends BaseRepository<Long, Customer> {

    Customer findByFirstName(String username);

    Customer findByAccountId(Long id);

    List<Customer> findAllByCriteria(CustomerSearch customerSearch);
}
