package org.example.eco.rating;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.rating.entity.Rating;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends GenericRepository<Rating, UUID> {
}
