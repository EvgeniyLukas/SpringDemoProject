package com.advboard.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    @Query("SELECT u FROM Advertisement u WHERE u.description LIKE :description ")
    List<Advertisement> findSimilar(@Param("description") String description);
}


