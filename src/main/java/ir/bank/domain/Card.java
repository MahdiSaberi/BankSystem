package ir.bank.domain;

import ir.bank.base.domain.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Card extends BaseEntity<Long> {


    private Long cardNumber;

    private Integer password;

    @ManyToMany
    @JoinTable(name = "card_record", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "record_id"))
    private Set<Record> recordSet;

    @OneToOne(mappedBy = "card")
    private Account account;

    public Card() {
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
