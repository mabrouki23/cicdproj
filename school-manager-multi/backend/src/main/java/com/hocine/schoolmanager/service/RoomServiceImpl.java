package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Room;
import com.hocine.schoolmanager.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repo;
    public RoomServiceImpl(RoomRepository repo) { this.repo = repo; }
    @Override public List<Room> findAll() { return repo.findAll(); }
    @Override public Optional<Room> findById(Long id) { return repo.findById(id); }
    @Override public Room save(Room r) { return repo.save(r); }
    @Override public void delete(Long id) { repo.deleteById(id); }
}
