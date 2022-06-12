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
    private Bank bank;

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

    public Bank getBank() {
        return bank;
    }

    public void setBankBranch(Bank bankBranch) {
        this.bank = bankBranch;
    }

    @Override
    public String toString() {
        return "Account{" +
                "customer=" + customer +
                ", card=" + card +
                ", bank=" + bank +
                '}';
    }
}
