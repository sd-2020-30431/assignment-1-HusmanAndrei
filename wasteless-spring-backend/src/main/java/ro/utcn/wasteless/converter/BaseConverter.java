package ro.utcn.wasteless.converter;

import ro.utcn.wasteless.domain.BaseEntity;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.dto.DTO;
import ro.utcn.wasteless.dto.UserDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseConverter<Model extends BaseEntity<Long>, DTO> implements Converter<Model, Long, DTO>
{
    public List<Model> convertAllToModels(List<DTO> dtos){
        List<Model> models = new ArrayList<>();
        dtos.forEach(dto -> models.add(convertToModel(dto)));
        return models;
    }

    public List<DTO> convertAllToDtos(List<Model> models){
        List<DTO> dtos = new ArrayList<>();
        models.forEach(model -> dtos.add(convertToDto(model)));
        return dtos;
    }

}
