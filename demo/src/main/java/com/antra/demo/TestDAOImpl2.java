package com.antra.demo;

import org.springframework.stereotype.Repository;

@Repository
public class TestDAOImpl2 implements TestDAO {
    @Override
    public void sameSomething(String str) {
        System.out.println("dao2 " + str);
    }
}
