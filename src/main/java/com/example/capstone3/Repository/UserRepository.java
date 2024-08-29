package com.example.capstone3.Repository;

import com.example.capstone3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    @Query("select u from User u where u.subscription_type = ?1")
    List<User> findUserBySubscription_type(String subscription_type);

}
