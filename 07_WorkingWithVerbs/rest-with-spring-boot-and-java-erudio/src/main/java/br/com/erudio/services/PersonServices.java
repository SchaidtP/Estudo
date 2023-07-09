package br.com.erudio.services;

import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all people!");
        List<Person> persons = new ArrayList<>();
        for (int x=0; x<8; x++){
            Person person = mockPerson(x);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFistName("Leandro");
        person.setLastName("Costa");
        person.setAddress("Uberlandia - Minas Gerais - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person) {
        logger.info("Finding one person!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person!");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one person!");
    }

    private Person mockPerson(int x) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFistName("Person name " + x);
        person.setLastName("Last name "  + x);
        person.setAddress("Some addrress in Brasil " + x);
        person.setGender("Male");
        return person;
    }
}
