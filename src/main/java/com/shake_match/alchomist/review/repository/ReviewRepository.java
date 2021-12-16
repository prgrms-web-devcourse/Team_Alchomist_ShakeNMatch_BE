package com.shake_match.alchomist.review.repository;

import com.shake_match.alchomist.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
