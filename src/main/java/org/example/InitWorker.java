package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.example.utils.Constants.DELIMITER;
import static org.example.utils.Constants.FILE_NOT_FOUND;


@PropertySource("classpath:application.properties")
public class InitWorker implements DAO {

    @Value("${app.env}")
    private String env;
    @Value("${app.init.path}")
    private String path;


    @Override
    public List<Contact> readFile() {
        List<Contact> contacts = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useLocale(Locale.ENGLISH);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] line1 = line.split(DELIMITER);
                contacts.add(new Contact(line1[0], line1[1], line1[2]));
            }

        } catch (FileNotFoundException e) {
            System.out.println(env);
            System.err.println(FILE_NOT_FOUND);
        }
        return contacts;
    }
}
