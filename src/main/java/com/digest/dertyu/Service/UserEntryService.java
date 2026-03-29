package com.digest.dertyu.Service;

import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntryService {

    private final UserRepository userRepository;

    public User createEntry(User user){
      return userRepository.save(user);
    }
    public List<User> getAllEntry(){
        return userRepository.findAll();
    }

    public Optional<User>getByJournalId(ObjectId myId) {
        return userRepository.findById(String.valueOf(myId));
    }

    public Boolean deleteByIdherE(ObjectId id) {
        userRepository.deleteById(String.valueOf(id));
        return true;
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public User updateUser(String name,User user) {
        User byUserName = userRepository.findByUserName(name);
        if(byUserName!=null){
            byUserName.setUserName(user.getUserName());
            byUserName.setPassword(user.getPassword());
        }
        userRepository.save(byUserName);
        return byUserName;
    }
}
