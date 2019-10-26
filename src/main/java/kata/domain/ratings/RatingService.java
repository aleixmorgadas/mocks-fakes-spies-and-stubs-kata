package kata.domain.ratings;

import java.util.Optional;

public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public void save(Rating rating) {
        repository.save(rating.id, rating);
    }

    public Optional<Rating> findById(RatingId id) {
        return repository.findById(id);
    }
}
