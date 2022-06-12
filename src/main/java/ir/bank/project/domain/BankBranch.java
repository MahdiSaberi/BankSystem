package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class BankBranch extends BaseEntity<Long> {

    @Column
    private String name;

    @Column
    @OneToMany
    private Set<Account> accounts;
}
