package ir.bank.project.domain;

import ir.bank.project.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transactions extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column
    private String title;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "card=" + card +
                ", title='" + title + '\'' +
                '}';
    }
}
