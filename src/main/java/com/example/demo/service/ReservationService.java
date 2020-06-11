package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelService hotelService;
    private final RoomService roomService;

    public ReservationDtoOutput add(ReservationDtoInput reservationDtoInput) {

        Reservation entity = Reservation.buildReservationEntity(reservationDtoInput);                          //Create and save an Reservation based on the input DTO

        entity.setCreatedAt(LocalDateTime.now());                                                               // Adding extra data to the entity (which wasn't included in request)
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setHotel(hotelService.getById(reservationDtoInput.getHotelId()).orElse(new Hotel()));
        entity.setRoom(roomService.getById(reservationDtoInput.getRoomId()).orElse(new Room()));

        return new ReservationDtoOutput(reservationRepository.save(entity));                                           // Converted to Output DTO and returned
    }

    public List<ReservationDtoOutput> getAll() {
        List<ReservationDtoOutput> reservationDtoOutputList = reservationRepository.findAll()
                .stream()
                .map((reservation) -> new ReservationDtoOutput(reservation)).collect(Collectors.toList());

        return reservationDtoOutputList;
    }

    public Optional<Reservation> getById(Long id) {

        return reservationRepository.findById(id);

    }

    public List<ReservationDtoOutput> getByHotelId(Long hotelId) {

        List<ReservationDtoOutput> reservations = reservationRepository.findAllByHotelId(hotelId)
                .stream()
                .map((reservation) -> new ReservationDtoOutput(reservation)).collect(Collectors.toList());

        return reservations;
    }

    public List<ReservationDtoOutput> getByRoomId(Long roomId) {

        List<ReservationDtoOutput> reservations = reservationRepository.findAllByHotelId(roomId)
                .stream()
                .map((reservation) -> new ReservationDtoOutput(reservation)).collect(Collectors.toList());

        return reservations;
    }


    public Optional<Reservation> delete(Long id) {

        Optional<Reservation> reservationToDelete = reservationRepository.findById(id);           //Generate an optional with the reservation to delete

        if (reservationToDelete.isPresent()) {                                        //If a reservation exists, we delete it and return it
            reservationRepository.deleteById(id);

            return reservationToDelete;
        }

        return reservationToDelete;                                                   //Else, just return the empty optional to be handled on controller layer
    }

    public ReservationDtoOutput replace(Long id, ReservationDtoInput reservationDtoInput) {

        Optional<Reservation> reservationToUpdate = reservationRepository.findById(id);           //Generate an optional with the reservation to update

//        reservationToUpdate.orElseThrow(()-> new NotFoundException)                 // To do. Is it ok to throw the exception on this layer ?


        Reservation entity = Reservation.buildReservationEntity(reservationDtoInput);             // Built entity and set the corresponding id before saving
        entity.setId(id);

        entity.setUpdatedAt(LocalDateTime.now());                                                 // Adding extra data to the entity (which wasn't included in request)
        entity.setHotel(hotelService.getById(reservationDtoInput.getHotelId()).orElse(new Hotel()));
        entity.setRoom(roomService.getById(reservationDtoInput.getRoomId()).orElse(new Room()));
        entity.setCreatedAt(reservationToUpdate.get().getCreatedAt());
        reservationRepository.save(entity);

        return new ReservationDtoOutput(entity);

    }
}