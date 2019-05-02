package com.lelis.contacts;

import com.lelis.contacts.model.Contact;
import com.lelis.contacts.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
public class ContactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}



	@Bean
	CommandLineRunner init(ContactRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						Contact c = new Contact();
						c.setName("Contact" + i);
						c.setEmail("contact" + i + "@email.com");
						c.setPhone("99999999999");
						return c;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

}
