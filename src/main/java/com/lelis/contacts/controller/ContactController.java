package com.lelis.contacts.controller;

import com.lelis.contacts.model.Contact;
import com.lelis.contacts.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactRepository repository;

    public ContactController(ContactRepository contactRepository){
        this.repository = contactRepository;
    }

    @GetMapping
    public List<Contact> findAllContact() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Contact> findContactById(@PathVariable Long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contact createContact(@Valid @RequestBody Contact contact) {
        return repository.save(contact);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        return repository.findById(id)
                .map(record ->{
                    repository.delete(record);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id,@Valid @RequestBody Contact contact) {
        return repository.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Contact updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
