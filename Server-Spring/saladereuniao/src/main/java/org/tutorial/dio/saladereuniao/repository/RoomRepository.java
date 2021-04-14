package org.tutorial.dio.saladereuniao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tutorial.dio.saladereuniao.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT COUNT(id) = 1 FROM meeting_room WHERE id=:id", nativeQuery = true)
    boolean containsId(@Param("id") Long id);

}
