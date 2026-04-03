package com.digest.dertyu.Controllers;

import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final UserEntryService userEntryService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Ok";
    }
    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            return new ResponseEntity<>(userEntryService.createNewEntry(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
