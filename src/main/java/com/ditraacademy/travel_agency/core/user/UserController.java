package com.ditraacademy.travel_agency.core.user;
import com.ditraacademy.travel_agency.core.user.models.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserController {

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    UserServices userServices;

    @PostMapping("/auth/register")
    public ResponseEntity<?> creatUser (@RequestBody User user){

        return userServices.creatUser(user);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn (@RequestBody SignInRequest signInRequest){
        return userServices.signInService(signInRequest);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userServices.getAllUsers();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User updatedUser){
      return userServices.updateUser(id, updatedUser);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        return userServices.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById (@PathVariable int id){
        return userServices.getUserById(id);
    }
}
