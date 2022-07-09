package ir.bank.service;

import ir.bank.domain.Customer;
import ir.bank.service.dto.CustomerSearch;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllByCriteria(CustomerSearch customerSearch);
}
