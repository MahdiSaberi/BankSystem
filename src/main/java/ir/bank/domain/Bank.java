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
    private String boss;

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

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}


