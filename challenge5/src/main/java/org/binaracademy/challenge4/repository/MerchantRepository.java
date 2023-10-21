package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update merchantdb m set status = :boolval where m.name = :mname")
    void queryUpdateMerchantStat(@Param("boolval") boolean stat, @Param("mname") String mname);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from merchantdb m where m.name = :mname")
    void queryDeleteByName(@Param("mname") String mname);

    @Query(nativeQuery = true, value = "select * from merchantdb as m where m.status = true")
    List<Merchant> queryActiveMerchant(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from merchantdb m where m.name = :mname")
    Merchant queryFindMerchantByName(@Param("mname") String name);
}
