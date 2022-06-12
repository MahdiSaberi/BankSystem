package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer extends BaseEntity<Long> {

    @Column(name = "full_name")
    private String fullName;

    @Column
    @OneToMany
    private Set<Account> accounts;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
