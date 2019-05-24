package pos.Pinterest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.Pinterest.model.User;
import pos.Pinterest.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User insertOne(User user){
        return userRepository.insertOne(user);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


}
