package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.profilePicture.imageId FROM User u Where u.userId= :userId")
    Integer getProfilePictureIdByUserId(int userId);
    
      Optional<User> findByEmail(String username);
}
