package ir.bank.base.repository;

import ir.bank.base.domain.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<ID extends Serializable, T extends BaseEntity<ID>> {

    void save(T t);

    T update(T t);

    void delete(T t);

    T find(T t);

    T findById(ID id);

}

