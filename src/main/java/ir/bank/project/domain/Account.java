package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.*;

@Entity
public class Account extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    private Card card;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bankBranch;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Bank getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(Bank bankBranch) {
        this.bankBranch = bankBranch;
    }
}
