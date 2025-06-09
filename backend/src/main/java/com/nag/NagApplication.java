package com.nag;

// import com.nag.model.AppUser;

import com.nag.model.Person;
// import com.nag.repository.AppUserRepository;
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

    // private final AppUserRepository appUserRepository;
    private final PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(NagApplication.class, args);
    }

    public NagApplication(PersonRepository personRepository) {
        // this.appUserRepository = appUserRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String[] args) throws Exception {
        // Add user objects and save these to the database
        Person person1 = new Person("4712345678", "John", "Smith", "josmi@mail.com", null, LocalDate.parse("1970-01-30"));
        Person person2 = new Person("4798765432", "Kathy", "Carpenter", "kacarp@mail.com", null, LocalDate.parse("1974-05-15"));

        personRepository.saveAll(Arrays.asList(person1, person2));

        for (Person person : personRepository.findAll()) {
            System.out.println(person.getFirstName() + " + " + person.getLastName());
        }

        //
        // appUserRepository.save(new AppUser("user", "$2a$10$B0pjYagH.fxMBpnHtyD1hemOnQFvxZma4RMQ.QdBm/OPPX4nB23LS", "USER"));
        // appUserRepository.save(new AppUser("admin", "$2a$10$G5yQTMz0480w.O0/VxTWGezMW2gsOBy42JpqNX26UXo4T7y2hC6bO", "ADMIN"));


    }
}
