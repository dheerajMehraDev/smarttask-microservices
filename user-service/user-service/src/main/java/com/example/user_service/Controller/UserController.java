package com.example.user_service.Controller;

import com.example.user_service.Entities.User;
import com.example.user_service.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {
    private final UserService service;

    UserController(UserService service){
        this.service = service;
    }
    // post - create a user
    @PostMapping("/createuser")
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        System.out.println("Received user: " + user);

        User createdUser =  service.createUser(user);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    // get - getMapping
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id1){
        return service.getUserById(id1)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // getAll
    @GetMapping("/getalluser")
    public ResponseEntity<List<User>> getAllUser(){
        return new  ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
    }

    // update
    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id){
        return new ResponseEntity<>(service.updateUserById(id) , HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteById(id);;
    }

}
