package com.example.jdbc_module.service;

import com.example.jdbc_module.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    Contact update(Contact contact);

    void deleteById(Long id);
}
