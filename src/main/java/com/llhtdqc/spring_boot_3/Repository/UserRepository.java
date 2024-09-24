package com.llhtdqc.spring_boot_3.Repository;

import com.llhtdqc.spring_boot_3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserName(String user_name);
}



