package com.example.booking.repository.specification;

import com.example.booking.dto.hotel.HotelFilterRequest;
import com.example.booking.entity.Hotel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {
    static Specification<Hotel> withFilter(HotelFilterRequest hotelFilterRequest) {
        return Specification.where(byId(hotelFilterRequest.getId()))
                .and(byName(hotelFilterRequest.getName()))
                .and(byTitle(hotelFilterRequest.getTitle()))
                .and(byCity(hotelFilterRequest.getCity()))
                .and(byAddress(hotelFilterRequest.getAddress()))
                .and(byDistanceToCentre(hotelFilterRequest.getDistanceToCentre()))
                .and(byRatingAndNumberOfRating(hotelFilterRequest.getRating(),hotelFilterRequest.getNumberOfRating()));
    }

    static Specification <Hotel> byId (Long id){
        return ((root, query, criteriaBuilder) -> {
            if (id == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    static Specification <Hotel> byName (String name){
        return ((root, query, criteriaBuilder) -> {
            if (name == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("name"), name);
        });
    }

    static Specification <Hotel> byTitle (String title){
        return ((root, query, criteriaBuilder) -> {
            if (title == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("title"), title);
        });
    }

    static Specification <Hotel> byCity (String city){
        return ((root, query, criteriaBuilder) -> {
            if (city == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("city"), city);
        });
    }

    static Specification <Hotel> byAddress (String address){
        return ((root, query, criteriaBuilder) -> {
            if (address == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("address"), address);
        });
    }

    static Specification <Hotel> byDistanceToCentre (Integer distanceToCentre){
        return ((root, query, criteriaBuilder) -> {
            if (distanceToCentre == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("distanceToCentre"), distanceToCentre);
        });
    }

    static Specification <Hotel> byRatingAndNumberOfRating (Double rating, Integer numberOfRating){
        return ((root, query, criteriaBuilder) -> {
            if (rating == null || numberOfRating == null){
                return null;
            }
            Predicate r = criteriaBuilder.equal(root.get("rating"),rating);
            Predicate n = criteriaBuilder.equal(root.get("numberOfRating"), numberOfRating);
            return criteriaBuilder.and(r,n);
        });
    }

}
