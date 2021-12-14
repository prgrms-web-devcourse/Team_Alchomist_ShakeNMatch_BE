package com.shake_match.alchomist.review.repository;

import com.shake_match.alchomist.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
