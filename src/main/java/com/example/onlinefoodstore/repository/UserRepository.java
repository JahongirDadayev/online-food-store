package com.example.onlinefoodstore.repository;

import com.example.onlinefoodstore.model.entities.User;
import com.example.onlinefoodstore.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    List<User> findByEnabled(boolean enabled);
}
