package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.utils.Constants.*;


@Component
public class ContactsWorker {
    private final DAO dao;
    private List<Contact> contacts;
    @Value("${app.save.path}")
    private String pathToSave;

    public ContactsWorker(DAO dao) {
        this.dao = dao;
        contacts = new ArrayList<>();
    }

    public void initFile() {
        contacts = dao.readFile();
    }

    public void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(FULL_NAME_INPUT_MESSAGE);
        String fullName = validator(scanner, FULL_NAME_REGEX, FULL_NAME_ERROR_MESSAGE);
        System.out.println(PHONE_NUMBER_INPUT_MESSAGE);
        String phoneNumber = validator(scanner, PHONE_NUMBER_REGEX, PHONE_NUMBER_ERROR_MESSAGE);
        System.out.println(EMAIL_INPUT_MESSAGE);
        String email = validator(scanner, EMAIL_REGEX, EMAIL_ERROR_MESSAGE);
        contacts.add(new Contact(fullName, phoneNumber, email));
    }

    public boolean removeContactByEmail(String email) {
        if (!email.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getEmail().equals(email)) {
                    contacts.remove(contacts.get(i));
                }
            }
        }
        return true;
    }

    public void printContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    public void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToSave, true))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
            }
            System.out.println(MESSAGE_OF_SAVED);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String validator(Scanner sc, String regex, String errorMessage) {
        String data = null;
        while (sc.hasNextLine()) {
            data = sc.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(data);
            if (!matcher.matches()) {
                System.out.println(errorMessage);
            } else break;
        }
        return data;
    }

}
