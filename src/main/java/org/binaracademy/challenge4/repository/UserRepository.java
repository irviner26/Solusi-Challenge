package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update userdb u set uname = :new where u.uname = :old")
    void queryUpdateUsername(@Param("new") String name, @Param("old") String oldName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update userdb u set password = :new where u.uname = :name")
    void queryUpdatePassword(@Param("new") String pass, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update userdb u set gmail = :new where u.uname = :name")
    void queryUpdateEmail(@Param("new") String mail, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from userdb u " +
            "where u.uname = :uname " +
            "and u.password = :pass " +
            "and u.gmail = :email")
    void queryDeleteUser(@Param("uname") String uname,
                         @Param("pass") String password,
                         @Param("email") String email);

    @Query(nativeQuery = true, value = "select * from userdb u where u.uname = :uname")
    User queryFindUserByName(@Param("uname") String uname);

    @Query(nativeQuery = true, value = "select * from userdb u where u.gmail = :email")
    User queryFindUserByEmail(@Param("email") String email);


    // Queries for Spring Security:
    Optional<User> findByUname(String uname);
    Boolean existsByUname(String uname);
    Boolean existsByGmail(String gmail);
    // End of queries.

}