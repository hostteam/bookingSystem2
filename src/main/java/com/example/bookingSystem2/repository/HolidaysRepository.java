package com.example.bookingSystem2.repository;

import com.example.bookingSystem2.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidaysRepository extends JpaRepository<Holiday, Integer> {
    Optional<Holiday> findById(Integer id);
    List<Holiday> findByUserId(Integer userId);
}
