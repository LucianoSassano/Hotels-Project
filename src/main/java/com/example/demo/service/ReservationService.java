package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.UncheckedReservation;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
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

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  private final RoomService roomService;
  private final UserService userService;

  public Reservation add(ReservationDtoInput reservationDtoInput) {

    Reservation reservation = Reservation.buildReservationEntity(reservationDtoInput);
    Room room = roomService.getById(reservationDtoInput.getRoomId());
    Hotel hotel = room.getHotel();
    User user = userService.findById(reservationDtoInput.getUserId());
    reservation.setRoom(room);
    reservation.setHotel(hotel);
    reservation.setUser(user);

    return (reservationRepository.save(reservation));
  }

  public List<Reservation> getAll() {

    return reservationRepository.findAll();
  }

  public Reservation getById(Long id) {
    return reservationRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
  }

  public List<Reservation> getAllByHotelId(Long hotelId) {

    return reservationRepository.findAllByHotelId(hotelId);
  }

  public List<Reservation> getAllByRoomId(Long roomId) {

    return reservationRepository.findAllByRoomId(roomId);
  }

  public Reservation delete(Long id) {

    Reservation reservationToDelete =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
    reservationRepository.deleteById(id);

    return reservationToDelete;
  }

  public Reservation replace(Long id, ReservationDtoInput reservationDtoInput) {

    reservationRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));

    Reservation reservation = Reservation.buildReservationEntity(reservationDtoInput);
    Room room = roomService.getById(reservationDtoInput.getRoomId());
    Hotel hotel = room.getHotel();
    User user = userService.findById(reservationDtoInput.getUserId());

    reservation.setId(id);
    reservation.setRoom(room);
    reservation.setHotel(hotel);
    reservation.setUser(user);

    reservationRepository.save(reservation);

    return reservation;
  }

  public Reservation partialUpdate(Long id, UncheckedReservation uncheckedReservation) {
    Reservation reservationToUpdate =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));

    Optional.ofNullable(uncheckedReservation.getRoomId())
        .ifPresent(
            roomId -> {
              reservationToUpdate.setRoom(roomService.getById(roomId));
              reservationToUpdate.setHotel(reservationToUpdate.getRoom().getHotel());
            });

    Optional.ofNullable(uncheckedReservation.getUserId())
        .ifPresent(userId -> reservationToUpdate.setUser(userService.findById(userId)));
    Optional.ofNullable(uncheckedReservation.getCheckIn())
        .ifPresent(
            checkin ->
                reservationToUpdate.setCheckIn(
                    LocalDate.parse(
                        SharedUtils.adjustLocalDate(checkin),
                        DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))));
    Optional.ofNullable(uncheckedReservation.getCheckOut())
        .ifPresent(
            checkOut ->
                reservationToUpdate.setCheckOut(
                    LocalDate.parse(
                        SharedUtils.adjustLocalDate(checkOut),
                        DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))));
    Optional.ofNullable(uncheckedReservation.getFinalPrice())
        .ifPresent(reservationToUpdate::setFinalPrice);
    Optional.ofNullable(uncheckedReservation.getIsPaid()).ifPresent(reservationToUpdate::setIsPaid);

    return reservationRepository.save(reservationToUpdate);
  }
}
