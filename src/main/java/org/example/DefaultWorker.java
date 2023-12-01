package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Constants.INIT_MESSAGE;


@Component
public class DefaultWorker implements DAO {
    @Value("${app.env}")
    private String env;


    @Override
    public List<Contact> readFile() {
        System.out.println(INIT_MESSAGE);
        return new ArrayList<>();
    }
}
