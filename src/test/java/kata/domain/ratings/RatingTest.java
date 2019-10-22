package kata.domain.ratings;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatingTest {
    private static final String title = Faker.instance().funnyName().name();

    @Test
    void ratingCannotBeLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> Rating.of(title, Rating.MIN_SCORE - 1));
    }

    @Test
    void ratingCannotBeMoreThan5() {
        assertThrows(IllegalArgumentException.class, () -> Rating.of(title, Rating.MAX_SCORE + 1));
    }

    @Test
    void ratingOf5IsValid() {
        final Rating rating = Rating.of(title, 5);

        assertEquals("Rating{title='" + title + "', score=5}", rating.toString());
    }

    @Test
    void ratingOf1IsValid() {
        final Rating rating = Rating.of(title, 1);

        assertEquals("Rating{title='" + title + "', score=1}", rating.toString());
    }
}
