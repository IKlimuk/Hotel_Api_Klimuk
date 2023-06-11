package com.robotdr.hotel_api.repository;

import com.robotdr.hotel_api.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.id not in (select b.room.id from Booking b where b.checkIn <= :dataEnd and b.checkOut >= :dataStart)")
    List<Room> findFreeRooms (@Param("dateStart") LocalDate dataStart, @Param("dateEnd") LocalDate dataEnd);

}
