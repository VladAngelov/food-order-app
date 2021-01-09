package com.foodorderapp.repositories;

import com.foodorderapp.models.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<Image> findByName(String name);
}

