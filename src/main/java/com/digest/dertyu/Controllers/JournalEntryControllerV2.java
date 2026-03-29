package com.digest.dertyu.Controllers;

import com.digest.dertyu.Entitites.JournalEntity;
import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Service.JournalEntryService;
import com.digest.dertyu.Service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class JournalEntryControllerV2 {
    //private Map<Long,JournalEntity>journalEntityMap=new HashMap<>();
    private final JournalEntryService journalEntryService;
    private final UserEntryService userEntryService;
    @GetMapping("/get/{name}")
    public ResponseEntity<List<JournalEntity>>getAllJournalEntriesOfUser(@PathVariable String name){
        User byUserName = userEntryService.findByUserName(name);
        List<JournalEntity> journalEntities = byUserName.getJournalEntities();
        try {
            return new ResponseEntity<>(journalEntities,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post/{name}")
    public ResponseEntity<JournalEntity>createJournal(@RequestBody JournalEntity journalEntity,
                                                      @PathVariable String name){
        try {
            return new ResponseEntity<>(journalEntryService.createEntry(journalEntity,name),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<Optional<JournalEntity>>getbyId(@PathVariable ObjectId myId){
       // return journalEntryService.getByJournalId(myId).orElse(null);
        Optional<JournalEntity> byJournalId = journalEntryService.getByJournalId(myId);
        if(byJournalId.isPresent()){
            return new ResponseEntity<>(byJournalId, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/{name}")
    public ResponseEntity<Boolean>deleteById(@PathVariable ObjectId id,@PathVariable String name){
        try {
            return new ResponseEntity<>(journalEntryService.deleteByIdherE(id,name),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/update/{id}/{name}")
    public ResponseEntity<JournalEntity>updateBYId(@PathVariable ObjectId id,
                                                   @RequestBody JournalEntity journalEntity,
                                                   @PathVariable String name){
        try {
            return new ResponseEntity<>(journalEntryService.updateyhis(id,journalEntity,name),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
