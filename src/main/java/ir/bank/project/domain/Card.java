package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Card extends BaseEntity<Long> {

    @Column
    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
