package com.example.booking.repository;

import com.example.booking.entity.UnavailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface UnavailableDateRepository extends JpaRepository <UnavailableDate, Long>, JpaSpecificationExecutor <UnavailableDate> {

    boolean existsByRoomIdAndDate(Long roomId, LocalDate date);
}
