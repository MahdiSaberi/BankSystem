package ir.bank.repository;

import ir.bank.base.repository.BaseRepository;
import ir.bank.domain.Card;
import ir.bank.domain.Customer;

public interface CardRepository extends BaseRepository<Long, Card> {

    Long generateNumber();

    Card findByCardNumber(Long cardNumber);

}
