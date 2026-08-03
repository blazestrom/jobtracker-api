package com.piyush.jobtracker.repository;
import com.piyush.jobtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {



}
