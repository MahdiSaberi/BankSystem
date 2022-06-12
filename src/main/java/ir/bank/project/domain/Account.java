package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account extends BaseEntity<Long> {

    @Column
    @ManyToOne
    private Customer customer;

    @Column
    @OneToOne
    private Card card;

    @Column
    @ManyToOne
    private BankBranch bankBranch;
}
