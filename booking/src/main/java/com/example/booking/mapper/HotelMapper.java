package com.example.booking.mapper;

import com.example.booking.dto.hotel.BriefHotelResponse;
import com.example.booking.dto.hotel.HotelListResponse;
import com.example.booking.dto.hotel.HotelResponse;
import com.example.booking.dto.hotel.UpsertHotelRequest;
import com.example.booking.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HotelMapper {

    Hotel RequestToHotel(UpsertHotelRequest request);

    @Mapping(source = "hotelId", target = "id")
    Hotel RequestToHotel(Long hotelId, UpsertHotelRequest request);

    HotelResponse hotelToResponse(Hotel hotel);

    BriefHotelResponse hotelToBriefResponse(Hotel hotel);

    default HotelListResponse hotelToHotelResponseList(List<Hotel> hotels){
        HotelListResponse response = new HotelListResponse();
        response.setHotels(hotels.stream()
                .map(this::hotelToResponse).collect(Collectors.toList()));
        return response;
    }
}


