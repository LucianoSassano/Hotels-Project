package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.UncheckedReservation;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository reservationRepository;

  private final RoomService roomService;
  private final UserService userService;

  public void checkExistenceOfAdjacentObjects(Long roomId, Long userId) {
    roomService.getById(roomId);
    userService.findById(userId);
  }

  public Reservation add(ReservationDtoInput reservationDtoInput) {
    checkExistenceOfAdjacentObjects(
        reservationDtoInput.getRoomId(), reservationDtoInput.getUserId());

    return (reservationRepository.save(Reservation.buildReservationEntity(reservationDtoInput)));
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

    checkExistenceOfAdjacentObjects(
        reservationDtoInput.getRoomId(), reservationDtoInput.getUserId());

    Reservation reservation = Reservation.buildReservationEntity(reservationDtoInput);
    reservation.setId(id);

    reservationRepository.save(reservation);

    return reservation;
  }

  public Reservation partialUpdate(Long id, UncheckedReservation patchDto) {
    Reservation reservation =
        reservationRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.RESERVATION_NOT_FOUND));

    reservation.update(patchDto);

    Optional.ofNullable(patchDto.getRoomId())
        .ifPresent(roomId -> reservation.setRoom(roomService.getById(roomId)));

    Optional.ofNullable(patchDto.getUserId())
        .ifPresent(userId -> reservation.setUser(userService.findById(userId)));

    return reservationRepository.save(reservation);
  }
}
