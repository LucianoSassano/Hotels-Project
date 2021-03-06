package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;

import com.example.demo.util.ErrorMessage;
import com.example.demo.util.UserUtils;
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
  private final UserService userService;

  public ReservationDtoOutput add(ReservationDtoInput reservationDtoInput) {

    Reservation reservation = Reservation.buildReservationEntity(reservationDtoInput);
    Room room = roomService.getById(reservationDtoInput.getRoomId());
    Hotel hotel = room.getHotel();
    User user = userService.findById(reservationDtoInput.getUserId());
    final LocalDateTime now = LocalDateTime.now();
    reservation.setCreatedAt(now);
    reservation.setUpdatedAt(now);
    reservation.setRoom(room);
    reservation.setHotel(hotel);
    reservation.setUser(user);

    return new ReservationDtoOutput(reservationRepository.save(reservation));
  }

  public List<ReservationDtoOutput> getAll() {

    return reservationRepository.findAll().stream()
        .map(ReservationDtoOutput::new)
        .collect(Collectors.toList());
  }

  public Reservation getById(Long id) {
    return reservationRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
  }

  public List<ReservationDtoOutput> getByHotelId(Long hotelId) {

    return reservationRepository.findAllByHotelId(hotelId).stream()
        .map(ReservationDtoOutput::new)
        .collect(Collectors.toList());
  }

  public List<ReservationDtoOutput> getByRoomId(Long roomId) {

    return reservationRepository.findAllByRoomId(roomId).stream()
        .map(ReservationDtoOutput::new)
        .collect(Collectors.toList());
  }

  public Reservation delete(Long id) {

    Reservation reservationToDelete =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));
    reservationRepository.deleteById(id);

    return reservationToDelete;
  }

  public ReservationDtoOutput replace(Long id, ReservationDtoInput reservationDtoInput) {

    Reservation reservationToUpdate =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));

    Reservation reservation = Reservation.buildReservationEntity(reservationDtoInput);
    LocalDateTime createdAt = reservationToUpdate.getCreatedAt();
    Room room = roomService.getById(reservationDtoInput.getRoomId());
    Hotel hotel = room.getHotel();
    User user = userService.findById(reservationDtoInput.getUserId());
    reservation.setId(id);
    reservation.setCreatedAt(createdAt);
    reservation.setUpdatedAt(LocalDateTime.now());
    reservation.setRoom(room);
    reservation.setHotel(hotel);
    reservation.setUser(user);

    reservationRepository.save(reservation);

    return new ReservationDtoOutput(reservation);
  }
}
