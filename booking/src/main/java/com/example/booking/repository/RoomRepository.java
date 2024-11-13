package com.example.booking.repository;

import com.example.booking.entity.Room;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Set;

public interface RoomRepository extends JpaRepository <Room, Long>, JpaSpecificationExecutor <Room> {

}
