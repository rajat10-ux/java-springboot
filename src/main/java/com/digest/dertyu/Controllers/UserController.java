package com.digest.dertyu.Controllers;

import com.digest.dertyu.Entitites.JournalEntity;
import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Service.JournalEntryService;
import com.digest.dertyu.Service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
private final UserEntryService userEntryService;

@GetMapping("/get")
public ResponseEntity<List<User>>getAllUsers(){
    try {
        return new ResponseEntity<>(userEntryService.getAllEntry()
                ,HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
@PostMapping("/create")
    public ResponseEntity<User>createUser(@RequestBody User user){
    try {
        return new ResponseEntity<>(userEntryService.createEntry(user),HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

@PatchMapping("/update")
    public ResponseEntity<User> updateUsers(@RequestParam String name,@RequestBody User user){
    try {
        return new ResponseEntity<>(userEntryService.updateUser(name,user),HttpStatus.ACCEPTED);
    } catch (Exception e) {
     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

}