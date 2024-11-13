package com.example.booking.service.impl;

import com.example.booking.dto.hotel.HotelFilterRequest;
import com.example.booking.entity.Hotel;
import com.example.booking.exception.EntityNotFoundException;
import com.example.booking.repository.HotelRepository;
import com.example.booking.repository.specification.HotelSpecification;
import com.example.booking.service.HotelService;
import com.example.booking.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> filterBy(HotelFilterRequest filter) {
        return hotelRepository.findAll(HotelSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    //@Override
    //public List<Hotel> findAll(PaginationRequest request) {
    //    return hotelRepository.findAll(request.pageRequest()).getContent();
    //}

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public Hotel save(Hotel hotel) {
        hotel.setRating(0.00);
        hotel.setNumberOfRating(0);
        hotel.setTotalRating(0.00);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        Hotel existedHotel = findById(hotel.getId());
        BeanUtils.copyNonNullProperties(hotel, existedHotel);
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void updateRating(Long hotelId, Integer newMark) {
        Hotel hotel = findById(hotelId);
        Double rating = hotel.getRating();
        Integer numberOfRating = hotel.getNumberOfRating() + 1;
        double totalRating;
        totalRating = rating * numberOfRating;
        totalRating = totalRating - rating + newMark;
        rating = totalRating / numberOfRating;
        hotel.setTotalRating(totalRating);
        hotel.setNumberOfRating(numberOfRating);
        hotel.setRating(Math.ceil(rating *100) / 100);
        update(hotel);
    }
}
