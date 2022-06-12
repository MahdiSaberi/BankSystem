package ir.bank.project.base.repository;

import ir.bank.project.base.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<E extends BaseEntity<ID>,ID extends Serializable> {

    E update(E e);

    void delete(E e);

    void deleteById(ID id);

    E findById(ID id);

    List<E> findAll();
}
