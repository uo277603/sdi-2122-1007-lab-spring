package com.uniovi.sdi21221007spring.repositories;

import com.uniovi.sdi21221007spring.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository  extends CrudRepository<User, Long> {
    User findByDni(String dni);
}
