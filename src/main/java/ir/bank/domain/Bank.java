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

    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<Account> account;

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}


