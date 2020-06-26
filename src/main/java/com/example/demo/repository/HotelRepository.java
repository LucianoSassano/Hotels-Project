package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

  @Query(value = "SELECT * FROM hotels h WHERE h.email = :email", nativeQuery = true)
  Optional<Hotel> findHotelByEmail(@Param("email") String email);

  @Modifying
  @Query(value = "UPDATE hotels h SET is_deleted=false WHERE h.hotel_id = :id", nativeQuery = true)
  void restoreHotelById(@Param("id") Long id);
}
