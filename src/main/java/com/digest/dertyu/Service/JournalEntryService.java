package com.digest.dertyu.Service;

import com.digest.dertyu.Entitites.JournalEntity;
import com.digest.dertyu.Repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final  JournalEntryRepository journalEntryRepository;

    public JournalEntity createEntry(JournalEntity journalEntity){
        journalEntity.setDate(LocalDateTime.now());
      return journalEntryRepository.save(journalEntity);
    }
    public List<JournalEntity>getAllEntry(){
        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntity>getByJournalId(ObjectId myId) {
        return journalEntryRepository.findById(String.valueOf(myId));
    }

    public Boolean deleteByIdherE(ObjectId id) {
        journalEntryRepository.deleteById(String.valueOf(id));
        return true;
    }


    public JournalEntity updateyhis(ObjectId id, JournalEntity journalEntity) {
        JournalEntity  old=journalEntryRepository.findById(String.valueOf(id)).orElse(null);
        if(old!=null){
            old.setContent(journalEntity.getContent());
            old.setTitle(journalEntity.getTitle());
        }
        journalEntryRepository.save(old);
        return old;
    }
}
