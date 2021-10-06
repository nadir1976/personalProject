package com.api.web;

import com.api.customized.errors.InputRequiredException;
import com.api.customized.errors.InputUserRequiredException;
import com.api.customized.errors.UserNotFoundException;
import com.api.customized.errors.UserSaveException;
import com.api.entities.User;
import com.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestControllerAPI {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/{nom}")
    public List<User> getUserByNom(@PathVariable(required = false) String nom) {

        if (!StringUtils.hasText(nom)) {
            throw new InputRequiredException(nom);
        }

        List<User> listeUserResultat= userRepository.findAll()
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(nom.toLowerCase()))
                .collect(Collectors.toList());

        if(listeUserResultat.isEmpty() ){
            throw  new UserNotFoundException(nom);
        }
        return listeUserResultat;

    }


    @PostMapping(value = "/save/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    ResponseEntity<Object> createUser(@RequestBody(required = false) User newUser) {

        if (newUser==null||!StringUtils.hasText(newUser.getName()) || newUser.getDateNaissance() == null || !StringUtils.hasText(newUser.getCountryResidence())) {
            throw new InputUserRequiredException(newUser);
        }

        if (newUser.getCountryResidence().equalsIgnoreCase("France")) {
            userRepository.save(newUser);
            return new ResponseEntity<Object>(newUser, HttpStatus.CREATED);
        }
        throw new UserSaveException(newUser.getName());

    }


}
