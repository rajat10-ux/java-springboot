package com.digest.dertyu.Controllers;

import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserEntryService userEntryService;

    @GetMapping("/all_users")
    public ResponseEntity<?> getAllUsers(){
        List<User> allEntry = userEntryService.getAllEntry();
        if(allEntry!=null && !allEntry.isEmpty()){
            return new ResponseEntity<>(allEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public void createAdmi(@RequestBody User user){
        userEntryService.createAdmin(user);
    }
}
