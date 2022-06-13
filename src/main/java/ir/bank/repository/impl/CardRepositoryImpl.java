package ir.bank.repository.impl;

import ir.bank.base.repository.impl.BaseRepositoryImpl;
import ir.bank.domain.Bank;
import ir.bank.domain.Card;
import ir.bank.domain.Customer;
import ir.bank.repository.BankRepository;
import ir.bank.repository.CardRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Random;

public class CardRepositoryImpl extends BaseRepositoryImpl<Long, Card> implements CardRepository {

    private EntityManagerFactory emf;

    public CardRepositoryImpl(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }

    @Override
    public Long generateNumber() {
        Random random = new Random();
        ArrayList<Long> cardNumber = new ArrayList<>();

        for (int i = 0; i < 16; i++)
            cardNumber.add(random.nextLong(9));

        Long cardToLong = 0L;
        for(Long n : cardNumber)
            cardToLong = 10 * cardToLong + n;

        return cardToLong;
    }

    @Override
    public Card findByCardNumber(Long cardNumber) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Card where cardNumber=:cardNumber", Card.class);
        query.setParameter("cardNumber",cardNumber);
        Card card = (Card) query.getSingleResult();
        return card;
    }

    @Override
    public Class<Card> getClassType() {
        return null;
    }
}
