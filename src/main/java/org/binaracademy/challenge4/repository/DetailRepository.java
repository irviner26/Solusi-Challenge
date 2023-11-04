package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, String> {

    @Query(nativeQuery = true, value = "select * from detaildb where order_id = :oid")
    List<Detail> querySelectDetail(@Param("oid") String orderID);
}
