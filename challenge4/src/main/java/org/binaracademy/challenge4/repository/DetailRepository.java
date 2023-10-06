package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, String> {
}
