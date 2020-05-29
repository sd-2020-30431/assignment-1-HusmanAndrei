package ro.utcn.wasteless.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.utcn.wasteless.domain.User;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements DTO<User, Long>{
    String name;
    String password;

    Long ID;

}
