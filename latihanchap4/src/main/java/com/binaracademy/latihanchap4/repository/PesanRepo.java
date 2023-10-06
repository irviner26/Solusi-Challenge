package com.binaracademy.latihanchap4.repository;

import com.binaracademy.latihanchap4.model.Pesan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PesanRepo extends JpaRepository<Pesan, String> {

    @Modifying
    @Query(nativeQuery = true, value = "insert into pesan (pesan_id, order_alamat, order_price, ojek_ojek_id)\n" +
            "select uuid_generate_v4(), :alamat, :harga, o.ojek_id\n" +
            "from ojek o\n" +
            "where o.ojek_nama = :nama")
    @Transactional
    void tambahDb(@Param("alamat") String alamat, @Param("harga") Integer harga, @Param("nama") String nama);


}
