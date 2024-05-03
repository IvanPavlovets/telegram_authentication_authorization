package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }


    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Optional<Person> save(Person person) {
        return Optional.of(personRepository.save(person));
    }

    /**
     * метод PATCH, который предназначен для частичного обновления данных.
     * подставляем найденому person, переданый password из DTO.
     * @return Optional<Person>
     */
    public Optional<Person> updatePatch(Person person) {
        var findedPerson = personRepository.findById(person.getId());
        if (findedPerson.isPresent()) {
            person.setPassword(person.getPassword());
            return this.save(person);
        }
        return findedPerson;
    }

    public Optional<Person> deleteById(int personId) {
        var findedPerson = personRepository.findById(personId);
        if (findedPerson.isPresent()) {
            personRepository.deleteById(personId);
        }
        return findedPerson;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = personRepository.findPersonByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.get().getLogin(), user.get().getPassword(), emptyList());
    }
}
