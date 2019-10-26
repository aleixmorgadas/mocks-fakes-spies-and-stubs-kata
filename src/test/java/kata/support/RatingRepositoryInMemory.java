package kata.support;

import kata.domain.ratings.Rating;
import kata.domain.ratings.RatingId;
import kata.domain.ratings.RatingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RatingRepositoryInMemory implements RatingRepository {
    private final Map<RatingId, Rating> db = new HashMap<>();

    @Override
    public void save(RatingId id, Rating rating) {
        db.put(id, rating);
    }

    @Override
    public Optional<Rating> findById(RatingId id) {
        return Optional.ofNullable(db.get(id));
    }
}
