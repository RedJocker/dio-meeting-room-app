package org.tutorial.dio.saladereuniao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tutorial.dio.saladereuniao.exception.RoomNotFoundException;
import org.tutorial.dio.saladereuniao.model.Room;
import org.tutorial.dio.saladereuniao.repository.RoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final String roomNotFoundMessageTemplate = "Room with id %d was not found";
    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RoomNotFoundException(String.format(roomNotFoundMessageTemplate, roomId))

                );
    }

    public boolean containsId(Long roomId) {
        return roomRepository.containsId(roomId);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room, Long roomId) {
        if(!roomRepository.containsId(roomId)) {
            throw new RoomNotFoundException(String.format(roomNotFoundMessageTemplate, roomId));
        } else  {
            room.setId(roomId);
            return roomRepository.save(room);
        }
    }

    public Room deleteRoom(Long roomId) {
        final Room roomToDelete = roomRepository.findById(roomId).orElseThrow(() ->
                new RoomNotFoundException(String.format(roomNotFoundMessageTemplate, roomId))
        );
        roomRepository.delete(roomToDelete);

        return roomToDelete;
    }
}
