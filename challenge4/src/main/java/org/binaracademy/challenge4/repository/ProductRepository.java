package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update productdb p " +
            "set name = :new " +
            "where p.name = :old " +
            "and p.merchant_code = (select m.code from merchantdb m where m.name = :mname)")
    void queryEditProductName(@Param("mname") String merchantName,@Param("old") String oldProdName,@Param("new") String newProdName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update productdb p " +
            "set price = :new " +
            "where p.name = :pname " +
            "and p.merchant_code = (select m.code from merchantdb m where m.name = :mname)")
    void queryEditProductPrice(@Param("mname") String merchantName,@Param("pname") String prodName,@Param("new") double newPrice);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from productdb pr where pr.name = :pname " +
            "and pr.merchant_code = (select m.code from merchantdb as m where m.name = :mname)")
    void queryDeleteProduct(@Param("pname")String productName,@Param("mname") String merchantName);

    @Query(nativeQuery = true, value = "select * from productdb " +
            "join merchantdb on merchantdb.code = productdb.merchant_code " +
            "where merchantdb.name = :name")
    Page<Product> queryFromCertainMerchant(@Param("name") String name, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from productdb p " +
            "where p.name = :pname " +
            "and p.merchant_code = (select m.code from merchantdb as m where m.name = :mname)")
    Product queryOneFromMerchant(@Param("pname") String prodName, @Param("mname") String MercName);

    @Query(nativeQuery = true, value = "select * from productdb " +
            "join merchantdb on merchantdb.code = productdb.merchant_code " +
            "where merchantdb.name = :name")
    List<Product> queryListOfProdFromMerch(@Param("name") String name);

    @Query(nativeQuery = true, value = "select * from productdb " +
            "join merchantdb on merchantdb.code = productdb.merchant_code " +
            "where merchantdb.name = :name and productdb.name = :pname")
    Product queryProdFromMerch(@Param("name") String name, @Param("pname") String pname);
}
