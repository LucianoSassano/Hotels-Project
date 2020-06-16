package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;

import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final RoomService roomService;

    public ReservationDtoOutput add(ReservationDtoInput reservationDtoInput) {

        Reservation entity = Reservation.buildReservationEntity(reservationDtoInput);                          //Create and save an Reservation based on the input DTO

        entity.setCreatedAt(LocalDateTime.now());                                                               // Adding extra data to the entity (which wasn't included in request)
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setRoom(roomService.getById(reservationDtoInput.getRoomId()));
        entity.setHotel((entity.getRoom().getHotel()));

        return new ReservationDtoOutput(reservationRepository.save(entity));                                           // Converted to Output DTO and returned
    }

    public List<ReservationDtoOutput> getAll() {

        return reservationRepository.findAll()
                .stream()
                .map(ReservationDtoOutput::new).collect(Collectors.toList());
    }

    public Reservation getById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
    }

    public List<ReservationDtoOutput> getByHotelId(Long hotelId) {

        return reservationRepository.findAllByHotelId(hotelId)
                .stream()
                .map(ReservationDtoOutput::new).collect(Collectors.toList());
    }

    public List<ReservationDtoOutput> getByRoomId(Long roomId) {

        return reservationRepository.findAllByRoomId(roomId)
                .stream()
                .map(ReservationDtoOutput::new).collect(Collectors.toList());
    }


    public Reservation delete(Long id) {

        Reservation reservationToDelete = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
        reservationRepository.deleteById(id);

        return reservationToDelete;
    }


    public ReservationDtoOutput replace(Long id, ReservationDtoInput reservationDtoInput) {

        Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));

        Reservation entity = Reservation.buildReservationEntity(reservationDtoInput);             // Built entity and set the corresponding id before saving
        entity.setId(id);

        entity.setUpdatedAt(LocalDateTime.now());                                                 // Adding extra data to the entity (which wasn't included in request)
        entity.setRoom(roomService.getById(reservationDtoInput.getRoomId()));
        entity.setHotel(entity.getRoom().getHotel());
        entity.setCreatedAt(reservationToUpdate.getCreatedAt());
        reservationRepository.save(entity);

        return new ReservationDtoOutput(entity);
    }
}
