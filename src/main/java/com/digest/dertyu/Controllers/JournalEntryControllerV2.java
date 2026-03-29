package com.digest.dertyu.Controllers;

import com.digest.dertyu.Entitites.JournalEntity;
import com.digest.dertyu.Service.JournalEntryService;
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
    @GetMapping("/get")
    public ResponseEntity<List<JournalEntity>>getAll(){
        try {
            return new ResponseEntity<>(journalEntryService.getAllEntry(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<JournalEntity>createJournal(@RequestBody JournalEntity journalEntity){
        try {
            return new ResponseEntity<>(journalEntryService.createEntry(journalEntity),HttpStatus.CREATED);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteByid(@PathVariable ObjectId id){
        try {
            return new ResponseEntity<>(journalEntryService.deleteByIdherE(id),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<JournalEntity>updateBYId(@PathVariable ObjectId id,@RequestBody JournalEntity journalEntity){
        try {
            return new ResponseEntity<>(journalEntryService.updateyhis(id,journalEntity),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
