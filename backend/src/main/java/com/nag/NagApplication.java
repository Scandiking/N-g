package com.nag;

import com.nag.model.AppUser;
import com.nag.model.Person;
import com.nag.repository.AppUserRepository;
import com.nag.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableMethodSecurity
public class NagApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(NagApplication.class);

    private final AppUserRepository appUserRepository;
    private final PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(NagApplication.class, args);
    }

    public NagApplication(AppUserRepository appUserRepository, PersonRepository personRepository) {
        this.appUserRepository = appUserRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String[] args) throws Exception {
        // Add user objects and save these to the database
        Person person1 = new Person("4712345678", "John", "Smith", "josmi@mail.com", null, LocalDate.parse("1970-01-30"));
        Person person2 = new Person("4798765432", "Kathy", "Carpenter", "kacarp@mail.com", null, LocalDate.parse("1974-05-15"));

        personRepository.saveAll(Arrays.asList(person1, person2));

        for (Person person : personRepository.findAll()) {
            logger.info(person.getFirstName() + " " + person.getLastName());
        }

        // Username: user, password: user
        appUserRepository.save(new AppUser(
                "user",
                "$2y$10$1yYXa.mXL1ZabeBJ3E3lvOG361jIT0oojJEBSM4fO1uwJsAE1892O",
                "USER"
        ));

        // Username: admin, password: admin
        appUserRepository.save(new AppUser(
                "admin",
                "$2y$10$5cD3SNsJQUwoHyjhhx0g0.3qz/e7KpEbbyxvctqBELPF1H/XQMF2a",
                "ADMIN"
        ));
    }
}
