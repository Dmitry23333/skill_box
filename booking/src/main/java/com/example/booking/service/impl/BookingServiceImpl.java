package com.example.booking.service.impl;

import com.example.booking.entity.Booking;
import com.example.booking.entity.Room;
import com.example.booking.entity.UnavailableDate;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.EntityNotFoundException;
import com.example.booking.repository.BookingRepository;
import com.example.booking.service.BookingService;
import com.example.booking.service.RoomService;
import com.example.booking.service.UnavailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UnavailableDateService unavailableDateService;
    private final RoomService roomService;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public Booking save(Booking booking) {
        List<LocalDate> dates = checkDate(booking);
        if (dates.isEmpty()) {
            throw new BookingException("For these dates the room is already occupied. Try choosing other dates");
        }
        for (LocalDate date : dates) {
            UnavailableDate unavailableDate = new UnavailableDate();
            unavailableDate.setRoom(booking.getRoom());
            unavailableDate.setDate(date);
            unavailableDateService.save(unavailableDate);
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private List<LocalDate> checkDate(Booking booking) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate arrivalDate = booking.getArrivalDate();
        while (!arrivalDate.equals(booking.getDepartureDate().plusDays(1))) {
            if (unavailableDateService.existsByRoomIdAndDate(booking.getRoom().getId(),
                    arrivalDate)) {
                dates.clear();
                break;
            }
            dates.add(arrivalDate);
            arrivalDate = arrivalDate.plusDays(1);
        }
        return dates;
    }
}
