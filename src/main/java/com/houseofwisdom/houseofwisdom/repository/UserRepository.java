package com.houseofwisdom.houseofwisdom.repository;

import com.houseofwisdom.houseofwisdom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
