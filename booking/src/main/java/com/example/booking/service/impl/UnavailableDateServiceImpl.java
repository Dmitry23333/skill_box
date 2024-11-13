package com.example.booking.service.impl;

import com.example.booking.entity.UnavailableDate;
import com.example.booking.repository.UnavailableDateRepository;
import com.example.booking.service.UnavailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnavailableDateServiceImpl implements UnavailableDateService {
    private final UnavailableDateRepository unavailableDateRepository;


    @Override
    public boolean existsByRoomIdAndDate(Long roomId, LocalDate date) {
        return unavailableDateRepository.existsByRoomIdAndDate(roomId,date);
    }

    @Override
    public List<UnavailableDate> findAll() {
        return unavailableDateRepository.findAll();
    }

    @Override
    public UnavailableDate save(UnavailableDate unavailableDate) {
        return unavailableDateRepository.save(unavailableDate);
    }

    @Override
    public UnavailableDate update(UnavailableDate unavailableDate) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
