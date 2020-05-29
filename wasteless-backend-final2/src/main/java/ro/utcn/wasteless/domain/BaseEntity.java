package ro.utcn.wasteless.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    ID id;

}
