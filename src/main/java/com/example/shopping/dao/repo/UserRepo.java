package com.example.shopping.dao.repo;

import com.example.shopping.dao.entity.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEnt,Long> {

//
//    @Query("select case when (count(u) > 0) then true else false end from UserEnt u where u.username=?1 and u.password=?2")
//    Boolean loginUser(String username,String password);


    @Query("select user from UserEnt user join user.loginDetails login  where login.username=?1")
    Optional<UserEnt> findUserEntByUsername(String username);

    @Query("select user from UserEnt user join user.loginDetails login where login.username=?1")
    Optional<UserEnt> findByUsername(String username);

}
