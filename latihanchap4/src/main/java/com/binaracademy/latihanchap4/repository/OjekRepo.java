package com.binaracademy.latihanchap4.repository;

import com.binaracademy.latihanchap4.model.Ojek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OjekRepo extends JpaRepository<Ojek, String> {

    @Query(nativeQuery = true, value = "select * from ojek as o where o.ojek_avail = :status")
    List<Ojek> driverStat(@Param("status") boolean status);

}
