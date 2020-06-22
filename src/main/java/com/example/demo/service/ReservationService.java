package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.repository.ReservationRepository;

import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  private final RoomService roomService;

  public Reservation add(ReservationDtoInput reservationDtoInput) {

    Reservation reservation = Reservation.buildReservationEntity(reservationDtoInput);
    Room room = roomService.getById(reservationDtoInput.getRoomId());
    Hotel hotel = room.getHotel();
    reservation.setRoom(room);
    reservation.setHotel(hotel);

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

    reservation.setId(id);
    reservation.setRoom(room);
    reservation.setHotel(hotel);

    reservationRepository.save(reservation);

    return reservation;
  }
}
