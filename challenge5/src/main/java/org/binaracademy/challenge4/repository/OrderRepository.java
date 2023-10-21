package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    //Query untuk ambil order dari usser tertentu
    @Query (nativeQuery = true, value = "select * from orderdb o where o.user_id = (select id from userdb u where u.uname = :uname)")
    List<Order> getOrdersOfUser(@Param("uname") String username);
}
