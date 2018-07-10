package com.antra.demo;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component //spring annotation: Component/Repository/Controller/Service
@Repository         //("dao")
@Primary
public class TestDAOImpl implements TestDAO {    //default name: testDAOImpl

    @Override
    public void sameSomething(String str) {
        System.out.println("dao1 " + str);
    }
}
