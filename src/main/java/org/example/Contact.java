package org.example;

import lombok.Getter;
import lombok.Setter;

import static org.example.utils.Constants.DELIMITER;

@Getter
@Setter
public class Contact {
    private String fullName;

    private String phoneNumber;

    private String email;


    public Contact(String fullName, String phoneNumber, String email) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return fullName + DELIMITER+ phoneNumber + DELIMITER + email + "\n";
    }
}
