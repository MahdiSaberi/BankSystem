package ir.bank.domain;

import ir.bank.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Bank extends BaseEntity<Long> {

    @Column
    private String name;

    @Column
    @OneToMany(mappedBy = "bank")
    private List<Employee> employees;

    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<Account> account;

    public Bank() {
    }

    public Bank(String name,Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}


