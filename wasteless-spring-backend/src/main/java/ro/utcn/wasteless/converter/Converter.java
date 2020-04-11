package ro.utcn.wasteless.converter;

import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.BaseEntity;
import ro.utcn.wasteless.dto.DTO;

import java.io.Serializable;

public interface Converter<T extends BaseEntity<ID>, ID extends Serializable, DTO> {
    T convertToModel(DTO dto);
    DTO convertToDto(T entity);
}
