package ro.utcn.wasteless.converter;

import org.springframework.stereotype.Component;
import ro.utcn.wasteless.domain.User;
import ro.utcn.wasteless.dto.UserDto;

@Component
public class UserConverter extends BaseConverter<User, UserDto>{
    @Override
    public User convertToModel(UserDto userDto) {
        User user = User.builder().username(userDto.getName()).password(userDto.getPassword()).build();
        Long id = userDto.getID();
        user.setId(id);
        return user;
    }

    @Override
    public UserDto convertToDto(User entity) {
        return UserDto.builder().name(entity.getUsername()).password(entity.getPassword()).ID(entity.getId()).build();
    }
}
