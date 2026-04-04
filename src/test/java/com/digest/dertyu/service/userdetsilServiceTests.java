package com.digest.dertyu.service;

import com.digest.dertyu.Service.UserDetailServiceImple;

public class userdetsilServiceTests {
    private UserDetailServiceImple userDetailServiceImple;


    void loadUserByUsernameTest(){
        userDetailServiceImple.loadUserByUsername("rajat");
    }
}