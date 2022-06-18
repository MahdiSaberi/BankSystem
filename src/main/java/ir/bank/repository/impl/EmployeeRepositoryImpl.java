package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Employee;
import ir.bank.repository.EmployeeRepository;

import javax.persistence.EntityManagerFactory;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Long, Employee> implements EmployeeRepository {



    public EmployeeRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Class<Employee> getClassType() {
        return Employee.class;
    }
}
