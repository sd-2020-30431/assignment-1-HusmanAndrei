package ro.utcn.wasteless.dto;

import ro.utcn.wasteless.domain.BaseEntity;

import java.io.Serializable;

public interface DTO<T extends BaseEntity<ID>, ID extends Serializable> extends Serializable{
    ID getID();
    void setID(ID id);
}
