package com.digest.dertyu.service;


import com.digest.dertyu.Repository.UserRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private  UserRepository userRepository;
    @Test
    public void testAdd(){
       assertEquals(10,5*2);
    }
    @Disabled
    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("rancho"));
        assertTrue(10>5);
        assertTrue(10<500);
    }
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,4,6",
            "3,5,8"
    })


    public void testFunc(int a,int b,int c){
        assertEquals(c,a+b);
    }

    @ParameterizedTest
    @CsvSource({
            "ramu",
            "rancho",
            "rajat",
            "ghanu"
    })
    public void testname(String name){
        assertNotEquals(userRepository.findByUserName(name),"failed for this"+ name);
    }
}
