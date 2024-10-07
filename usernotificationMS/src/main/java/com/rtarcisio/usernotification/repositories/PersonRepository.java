package com.rtarcisio.usernotification.repositories;

import com.rtarcisio.usernotification.domains.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String login);

    Optional<Person> findByCpf(String cpf);
}
