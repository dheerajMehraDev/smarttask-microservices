package com.example.user_service.Service;

import com.example.user_service.Entities.User;
import com.example.user_service.Repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private  final UserRepository repo ;
    UserService(UserRepository repo){
        this.repo = repo;
    }


    public User createUser(User user) {
        return repo.save(user);
    }



    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    public List<User> getAllUser() {
       return repo.findAll();
    }

    public void deleteById(Long id) {
         repo.deleteById(id);
    }

    public User updateUserById(Long id) {
      return repo.findById(id)
               .map(user ->  repo.save(user))
               .orElse(null);
    }
}
