package com.digest.dertyu.Service;

import com.digest.dertyu.Entitites.JournalEntity;
import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Exceptions.TransactionException;
import com.digest.dertyu.Repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final  JournalEntryRepository journalEntryRepository;
    private final UserEntryService userEntryService;
    @Transactional
    public JournalEntity createEntry(JournalEntity journalEntity, String name){
        try {
            User byUserName = userEntryService.findByUserName(name);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalEntryRepository.save(journalEntity);
            byUserName.getJournalEntities().add(saved);
            userEntryService.createEntry(byUserName);
            return saved;
        } catch (Exception e) {
            throw new TransactionException(e.getMessage());
        }
    }

    public List<JournalEntity>getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity>getByJournalId(ObjectId myId) {
        return journalEntryRepository.findById(String.valueOf(myId));
    }

    public Boolean deleteByIdherE(ObjectId id, String name) {
        User byUserName = userEntryService.findByUserName(name);
        List<JournalEntity> journalEntities = byUserName.getJournalEntities();
        journalEntities.removeIf(i -> i.getId().equals(id));
        userEntryService.createEntry(byUserName);
        journalEntryRepository.deleteById(String.valueOf(id));
        return true;
    }


    public JournalEntity updateyhis(ObjectId id, JournalEntity journalEntity, String name) {
        JournalEntity  old=journalEntryRepository.findById(String.valueOf(id)).orElse(null);
        if(old!=null){
            old.setContent(journalEntity.getContent());
            old.setTitle(journalEntity.getTitle());
        }
        journalEntryRepository.save(old);
        return old;
    }
}
