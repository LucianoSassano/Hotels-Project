package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.dto.reservation.UncheckedReservation;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;

import com.example.demo.util.Constants;
import com.example.demo.util.ErrorMessage;
import com.example.demo.util.SharedUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
        List<ReservationDtoOutput> reservationDtoOutputList = reservationRepository.findAll()
                .stream()
                .map((reservation) -> new ReservationDtoOutput(reservation)).collect(Collectors.toList());

        if (reservationDtoOutputList.isEmpty())
            throw new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND);

        return reservationDtoOutputList;
    }

    public Reservation getById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
    }

    public List<ReservationDtoOutput> getByHotelId(Long hotelId) {

        List<ReservationDtoOutput> reservations = reservationRepository.findAllByHotelId(hotelId)
                .stream()
                .map((reservation) -> new ReservationDtoOutput(reservation)).collect(Collectors.toList());

        if (reservations.isEmpty())
            throw new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND);

        return reservations;
    }

    public List<ReservationDtoOutput> getByRoomId(Long roomId) {

        List<ReservationDtoOutput> reservations = reservationRepository.findAllByRoomId(roomId)
                .stream()
                .map((reservation) -> new ReservationDtoOutput(reservation)).collect(Collectors.toList());

        if (reservations.isEmpty())
            throw new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND);

        return reservations;
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

    public ReservationDtoOutput partialUpdate(Long id, UncheckedReservation uncheckedReservation) {
        Reservation reservationToUpdate = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));

        reservationToUpdate.setUpdatedAt(LocalDateTime.now());

        Optional.ofNullable(uncheckedReservation.getRoomId())
                .ifPresent(roomId -> {
                    reservationToUpdate.setRoom(roomService.getById(roomId));
                    reservationToUpdate.setHotel(reservationToUpdate.getRoom().getHotel());
                });
//      Optional.ofNullable(uncheckedReservation.getUserId()).ifPresent(userId->reservationToUpdate.setUser(userService.getById(userId)));
        Optional.ofNullable(uncheckedReservation.getCheckIn()).ifPresent(checkin -> reservationToUpdate.setCheckIn(LocalDate.parse(SharedUtils.adjustLocalDate(checkin), DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))));
        Optional.ofNullable(uncheckedReservation.getCheckOut()).ifPresent(checkOut -> reservationToUpdate.setCheckOut(LocalDate.parse(SharedUtils.adjustLocalDate(checkOut), DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))));
        Optional.ofNullable(uncheckedReservation.getFinalPrice()).ifPresent(reservationToUpdate::setFinalPrice);
        Optional.ofNullable(uncheckedReservation.getIsPaid()).ifPresent(reservationToUpdate::setIsPaid);

        return new ReservationDtoOutput(reservationRepository.save(reservationToUpdate));
    }


}
