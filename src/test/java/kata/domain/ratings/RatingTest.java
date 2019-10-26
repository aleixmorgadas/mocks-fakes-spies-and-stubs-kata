package kata.domain.ratings;

import com.github.javafaker.Faker;
import kata.domain.user.UserId;
import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatingTest {
    private static final String title = Faker.instance().funnyName().name();

    @Test
    void ratingCannotBeLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> Rating.of(title, Rating.MIN_SCORE - 1, UserIdDummy.randomUserId()));
    }

    @Test
    void ratingCannotBeMoreThan5() {
        assertThrows(IllegalArgumentException.class, () -> Rating.of(title, Rating.MAX_SCORE + 1, UserIdDummy.randomUserId()));
    }

    @Test
    void titleAndUserIdMustNotBeNull() {
        assertThrows(NullPointerException.class, () -> Rating.of(null, Rating.MAX_SCORE, null));
    }

    @Test
    void ratingOf5IsValid() {
        final Rating rating = Rating.of(title, 5, UserId.of("aUsername"));

        assertEquals("Rating{id='" + rating.id.toString() + "', " +
                "title='" + title + "', " +
                "score=5, userId=UserId{value='aUsername'}}", rating.toString());
    }

    @Test
    void ratingOf1IsValid() {
        final Rating rating = Rating.of(title, 1, UserId.of("aUsername"));

        assertEquals("Rating{id='" + rating.id.toString() + "', " +
                "title='" + title + "', " +
                "score=1, " +
                "userId=UserId{value='aUsername'}}", rating.toString());
    }
}
