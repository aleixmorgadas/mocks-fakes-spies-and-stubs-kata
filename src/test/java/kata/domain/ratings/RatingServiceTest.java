package kata.domain.ratings;

import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class RatingServiceTest {
    private RatingRepository repository;
    private RatingService ratingService;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(RatingRepository.class);
        ratingService = new RatingService(repository);
    }

    @Test
    void shouldSaveInTheRepository() {
        final Rating rating = Rating.of("aTitle", 4, UserIdDummy.randomUserId());

        ratingService.save(rating);

        verify(repository).save(rating.id, rating);
    }
}
