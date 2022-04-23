package com.example.shopping.dao.repo;

import com.example.shopping.dao.entity.PublisherEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
@EnableJpaRepositories
public interface PublisherRepo extends JpaRepository<PublisherEnt,Long> {



    Optional<PublisherEnt> getPublisherByName(String username);
}
