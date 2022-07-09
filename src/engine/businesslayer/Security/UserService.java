package engine.businesslayer.Security;

import engine.persistencelayer.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserCrudRepository userCrudRepository;

    @Autowired
    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public Optional<User> findById(String username) {
        return userCrudRepository.findById(username);
    }

    public User save(User user) {
        return userCrudRepository.save(user);
    }

    public boolean existsById(String username) {
        return userCrudRepository.existsById(username);
    }



}
