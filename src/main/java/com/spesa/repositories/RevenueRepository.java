package com.spesa.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spesa.models.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, UUID> {

    public List<Revenue> findByUserId(String id);

    @Query("SELECT SUM(r.value) FROM Revenue r WHERE r.userId = :cond")
    public Double revenueTotal(@Param("cond") String id);
}
