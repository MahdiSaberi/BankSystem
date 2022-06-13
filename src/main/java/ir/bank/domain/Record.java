package ir.bank.domain;

import ir.bank.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Record extends BaseEntity<Long> {

    private String message;

    @ManyToMany
    private Set<Card> cardSet;

    public Record() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Card> getCardSet() {
        return cardSet;
    }

    public void setCardSet(Set<Card> cardSet) {
        this.cardSet = cardSet;
    }
}
