package ir.bank.domain;

import ir.bank.base.domain.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Card extends BaseEntity<Long> {

    @Column(name = "card_number")
    private Long cardNumber;

    private Integer password;

    private Long money;

    @Column(name = "expiration")
    private LocalDate date;

    @Column(name = "record")
    @OneToMany
    //@JoinTable(name = "card_record", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "record_id"))
    private List<Records> recordList;

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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Records> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Records> recordList) {
        this.recordList = recordList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
