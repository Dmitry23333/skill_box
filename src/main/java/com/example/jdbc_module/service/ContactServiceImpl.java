package com.example.jdbc_module.service;

import com.example.jdbc_module.Contact;
import com.example.jdbc_module.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.update(contact);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
