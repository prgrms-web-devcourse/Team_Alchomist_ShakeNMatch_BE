package com.shake_match.alchomist.amazon.repository;

import com.shake_match.alchomist.amazon.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    @Override
    List<Gallery> findAll();
}
