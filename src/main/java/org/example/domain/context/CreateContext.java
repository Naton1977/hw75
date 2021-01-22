package org.example.domain.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreateContext {

    private static CreateContext instance;

    public static CreateContext getInstance() {
        if (instance == null) {
            instance = new CreateContext();
        }
        return instance;

    }


    private CreateContext() {

    }

    public ApplicationContext returnContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        return context;
    }


}
