package com.lelis.contacts.service;

import com.lelis.contacts.repository.ContactRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import com.lelis.contacts.model.Contact;

@Service
public class ContactService {

    private ContactRepository repository;

    public ContactService(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }

    public List<Contact> listAllContacts() {
        return this.repository.findAll();
    }

    /* private Boolean validateContact(Contact contact) {

        if(contact.getName().isEmpty()) {
            return false;
        } else if(contact.getName().length() < 3 || contact.getName().length() > 50) {
            return false;
        }

        if(contact.getEmail().isEmpty()) {
            return false;
        }

        if(contact.getPhone().contains(" ")) {
            return false;
        } else if(contact.getPhone().length() > 11) {
            return false;
        }

        return true;
    } */

}