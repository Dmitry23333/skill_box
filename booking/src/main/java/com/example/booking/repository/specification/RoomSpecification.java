package com.example.booking.repository.specification;

import com.example.booking.dto.room.RoomFilterRequest;
import com.example.booking.entity.Booking;
import com.example.booking.entity.Room;
import com.example.booking.entity.UnavailableDate;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


public interface RoomSpecification {
    static Specification<Room> withFilter(RoomFilterRequest roomFilterRequest) {
        return Specification.where(byId(roomFilterRequest.getId()))
                .and(byTitle(roomFilterRequest.getTitle()))
                .and(hasPriceBetween(roomFilterRequest.getMinPrice(), roomFilterRequest.getMaxPrice()))
                .and(byCapacity(roomFilterRequest.getCapacity()))
                .and(isAvailableBetweenDates(roomFilterRequest.getArrivalDate(),roomFilterRequest.getDepartureDate()))
                .and(byHotelId(roomFilterRequest.getHotelId()));
    }

    static Specification<Room> byId(Long id) {
        return ((root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    static Specification<Room> byTitle(String title) {
        return ((root, query, criteriaBuilder) -> {
            if (title == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("title"), title);
        });
    }

    static Specification<Room> hasPriceBetween(Double minPrice, Double maxPrice) {
        return ((root, query, criteriaBuilder) -> {
            if (minPrice == null || maxPrice == null) {
                return null;
            }
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);

        });
    }

    static Specification<Room> byCapacity(Integer capacity) {
        return ((root, query, criteriaBuilder) -> {
            if (capacity == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("capacity"), capacity);

        });
    }

    static Specification<Room> isAvailableBetweenDates(LocalDate arrivalDate, LocalDate departureDate) {
        return ((root, query, criteriaBuilder) -> {
            if (arrivalDate == null || departureDate == null) {
                System.out.println("Tut");
                return null;
            }
            LocalDate s = arrivalDate;
            Predicate p = null;
            while (!s.equals(departureDate.plusDays(1))){
                 p = criteriaBuilder.equal(root.get("unavailableDates").get("id"));
                s.plusDays(1);
            }
        return criteriaBuilder.and(p);
        });
    }

    static Specification <Booking> getSomething(){
        return (root, query, criteriaBuilder) -> criteriaBuilde.a
    }

    static Specification<Room> byHotelId(Long id) {
        return ((root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("hotel").get("id"), id);
        });
    }

}