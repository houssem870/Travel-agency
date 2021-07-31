package com.ditraacademy.travel_agency.core.user;

import com.ditraacademy.travel_agency.core.user.models.SignInRequest;
import com.ditraacademy.travel_agency.core.user.models.SignInResponse;
import com.ditraacademy.travel_agency.utils.ErrorResponseModel;
import com.ditraacademy.travel_agency.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> creatUser(User user) {
        if (user.getName()==null)
            return new ResponseEntity<>(new ErrorResponseModel("user name required"),HttpStatus.BAD_REQUEST);
        if (user.getName().length()<3)
            return new ResponseEntity<>(new ErrorResponseModel("wrong user name"),HttpStatus.BAD_REQUEST);
        if (user.getAge()==null)
            return new ResponseEntity<>(new ErrorResponseModel("user age required"),HttpStatus.BAD_REQUEST);
        if (user.getAge()<0)
            return new ResponseEntity<>(new ErrorResponseModel("wrong user age"),HttpStatus.BAD_REQUEST);

        String password = passwordEncoder().encode(user.getPassword());
        user.setPassword(password);

        user=userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public ResponseEntity<?> updateUser(int id, User updatedUser) {
        Optional<User> UserOptional = userRepository.findById(id);
        if(!UserOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseModel("wrong user id "),HttpStatus.BAD_REQUEST);

        User databaseUser = UserOptional.get();

        if(updatedUser.getName() != null)
            if (updatedUser.getName().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModel("wrong user name"),HttpStatus.BAD_REQUEST);
            else
                databaseUser.setName(updatedUser.getName());

        if(updatedUser.getAge() != null)
            if (updatedUser.getAge() < 0)
                return new ResponseEntity<>(new ErrorResponseModel("wrong user age"),HttpStatus.BAD_REQUEST);
            else
                databaseUser.setAge(updatedUser.getAge());

        userRepository.save(databaseUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> deleteUser(int id) {
        Optional<User> databaseUserOptional = userRepository.findById(id);
        if (! databaseUserOptional.isPresent()){
            ErrorResponseModel errorResponseModel=new ErrorResponseModel("user id not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);

        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> getUserById (int id){
        Optional<User> OpUser=userRepository.findById(id);
        if(! OpUser.isPresent()){
            ErrorResponseModel errorResponseModel= new ErrorResponseModel("wrong user id");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        User User=OpUser.get();
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{

        Optional<User> userOptional=userRepository.findByUsername(username);

        if (!userOptional.isPresent())
            return null;

        String password= userOptional.get().getPassword();
        return new org.springframework.security.core.userdetails.User (username,password, AuthorityUtils.NO_AUTHORITIES);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> signInService (SignInRequest signInRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        String token =jwtUtils.generateToken(signInRequest.getUsername());
        return  new ResponseEntity<>(new SignInResponse(token),HttpStatus.OK);
    }
}