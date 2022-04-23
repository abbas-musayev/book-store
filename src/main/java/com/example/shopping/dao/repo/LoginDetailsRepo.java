package com.example.shopping.dao.repo;

import com.example.shopping.dao.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDetailsRepo extends JpaRepository<LoginDetails,Long> {


    Optional<LoginDetails> findLoginEntByUsername(String username);
}