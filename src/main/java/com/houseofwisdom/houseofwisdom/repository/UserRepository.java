package com.houseofwisdom.houseofwisdom.repository;

import com.houseofwisdom.houseofwisdom.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
