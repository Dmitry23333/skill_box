package com.example.booking.service.impl;

import com.example.booking.dto.hotel.HotelFilterRequest;
import com.example.booking.dto.room.RoomFilterRequest;
import com.example.booking.entity.Hotel;
import com.example.booking.entity.Room;
import com.example.booking.exception.EntityNotFoundException;
import com.example.booking.repository.RoomRepository;
import com.example.booking.repository.specification.HotelSpecification;
import com.example.booking.repository.specification.RoomSpecification;
import com.example.booking.service.RoomService;
import com.example.booking.utils.BeanUtils;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public List <Room> filterBy(RoomFilterRequest filter){
        return roomRepository.findAll(RoomSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    };

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(MessageFormat.format("Entity by ID {0} not found", id)));
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        Room existedRoom = findById(room.getId());
        BeanUtils.copyNonNullProperties(room, existedRoom);
        return roomRepository.save(room);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

}
