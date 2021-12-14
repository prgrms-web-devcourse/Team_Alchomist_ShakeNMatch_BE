package com.shake_match.alchomist.amazon.repository;

import com.shake_match.alchomist.amazon.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Override
    List<Image> findAll();
}
