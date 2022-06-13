package ir.bank.base.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue
    protected ID id;

    public BaseEntity() {
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
