package kata.support;

import kata.domain.ratings.Rating;
import kata.domain.ratings.RatingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RatingRepositoryInMemory implements RatingRepository {
    private final Map<String, Rating> db = new HashMap<>();

    @Override
    public void save(String id, Rating rating) {
        db.put(id, rating);
    }

    @Override
    public Optional<Rating> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }
}
