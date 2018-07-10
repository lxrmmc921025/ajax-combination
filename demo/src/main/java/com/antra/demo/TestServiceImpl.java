package com.antra.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")     //singleton(default)/prototype/request/session
public class TestServiceImpl implements TestService {

    //1.
    @Autowired //dependency injection
//    @Qualifier("testDAOImpl2")
            TestDAO tdao;


//    TestDAO tdao;
//2. constructor
//    @Autowired
//    TestServiceImpl(TestDAO tdao){
//        //do something before
//        this.tdao = tdao;
//        //do something after
//    }


//3. setter
//    @Autowired
//    public void setTdao(TestDAO tdao) {
//        this.tdao = tdao;
//    }

    @Override
    public void save() {
        tdao.sameSomething("service ");
    }
}
