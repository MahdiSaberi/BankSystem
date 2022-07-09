package ir.bank.service.impl;

import ir.bank.base.service.BaseService;
import ir.bank.base.service.impl.BaseServiceImpl;
import ir.bank.domain.Customer;
import ir.bank.repository.CustomerRepository;
import ir.bank.service.CustomerService;
import ir.bank.service.dto.CustomerSearch;

import java.util.List;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository>
        implements CustomerService {

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    public List<Customer> findAllByCriteria(CustomerSearch customerSearch){
        return repository.findAllByCriteria(customerSearch);
    }
}
