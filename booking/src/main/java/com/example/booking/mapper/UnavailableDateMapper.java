package com.example.booking.mapper;

import com.example.booking.dto.unavaialbledate.UnavailableDateResponse;
import com.example.booking.entity.UnavailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UnavailableDateMapper {
    UnavailableDateResponse dateToResponse(UnavailableDate dates);

}
