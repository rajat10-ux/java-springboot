package com.digest.dertyu.Service;

import com.digest.dertyu.Entitites.User;
import com.digest.dertyu.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEntryService {

    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    //private static final Logger logger= (Logger) LoggerFactory.getLogger(UserEntryService.class);

    public User createEntry(User user){
      return userRepository.save(user);
    }

    public User createNewEntry(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("error for {}",user.getUserName(),e);
            log.debug("hgjkgv");
            log.warn("hgjkgv");
            log.info("hgjkgv");
            log.trace("hgjkgv");
            throw new RuntimeException(e);
        }
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
        createNewEntry(byUserName);
        return byUserName;
    }

    public void createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
        userRepository.save(user);
    }
}
