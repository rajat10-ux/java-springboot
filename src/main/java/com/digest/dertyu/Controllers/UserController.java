package com.digest.dertyu.Controllers;

import com.digest.dertyu.Entitites.JournalEntity;
import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Repository.UserRepository;
import com.digest.dertyu.Service.JournalEntryService;
import com.digest.dertyu.Service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserEntryService userEntryService;
    private final UserRepository userRepository;
    @PatchMapping("/update")
    public ResponseEntity<User> updateUsers(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        try {
        return new ResponseEntity<>(userEntryService.updateUser(name,user),HttpStatus.ACCEPTED);
    } catch (Exception e) {
     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        userRepository.deleteByUserName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}