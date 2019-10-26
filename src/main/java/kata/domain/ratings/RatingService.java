package kata.domain.ratings;

public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public void save(Rating rating) {
        repository.save(rating.id, rating);
    }
}
