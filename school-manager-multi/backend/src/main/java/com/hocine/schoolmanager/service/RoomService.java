package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> findAll();
    Optional<Room> findById(Long id);
    Room save(Room r);
    void delete(Long id);
}
