package ro.utcn.wasteless.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.wasteless.domain.BaseEntity;

import java.io.Serializable;

public interface BaseRepository<T extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<T, ID> {

}