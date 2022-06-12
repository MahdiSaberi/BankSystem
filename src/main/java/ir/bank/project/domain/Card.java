package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Card extends BaseEntity<Long> {

    @Column
    @OneToOne
    private Account account;
}
