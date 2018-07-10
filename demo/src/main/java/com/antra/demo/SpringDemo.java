package com.antra.demo;

import com.antra.demo.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDemo {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        //        TestDAO dao = (TestDAO) ac.getBean("testDAOImpl2");      //interface type-->loosely coupled
        //        dao.sameSomething("lalalalala");

        //container: singleton scope always create 1 obj
        TestService tservice1 = (TestService) ac.getBean("testServiceImpl");
        tservice1.save();
//        TestService tservice2 = (TestService) ac.getBean("testServiceImpl");
//        System.out.println(tservice1 == tservice2);

    }
}
