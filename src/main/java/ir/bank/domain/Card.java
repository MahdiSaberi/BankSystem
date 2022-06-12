package ir.bank.domain;

import ir.bank.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Card extends BaseEntity<Long> {

    @OneToOne
    private Account account;

    public Card() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
