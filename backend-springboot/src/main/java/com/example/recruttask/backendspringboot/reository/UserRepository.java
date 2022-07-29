package com.example.recruttask.backendspringboot.reository;

import com.example.recruttask.backendspringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u where " +
            "(:name is null or :name='' or lower(u.name) like lower(concat('%', :name,'%'))) or" +
            "(:surname is null or :surname='' or lower(u.surname) like lower(concat('%', :surname,'%'))) or" +
            "(:login is null or :login='' or lower(u.login) like lower(concat('%', :login,'%')))")
    Page<User> findByParams(@Param("name") String name,
                            @Param("surname") String surname,
                            @Param("login") String login,
                            Pageable pageable);

}
