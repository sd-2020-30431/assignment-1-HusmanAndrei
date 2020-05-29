package ro.utcn.wasteless.repository;

import org.springframework.stereotype.Repository;
import ro.utcn.wasteless.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long>
{

    List<User> findByUsernameAndPassword(String username, String password);


}
