package com.example.demo.repository;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  List<Reservation> findAllByHotelId(Long hotelId);

  List<Reservation> findAllByRoomId(Long roomId);
}
