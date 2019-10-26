package kata.domain.rate;

import com.github.javafaker.Faker;
import kata.domain.user.UserId;
import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RateTest {
    private static final String title = Faker.instance().funnyName().name();

    @Test
    void ratingCannotBeLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> Rate.of(title, Rate.MIN_SCORE - 1, UserIdDummy.randomUserId()));
    }

    @Test
    void ratingCannotBeMoreThan5() {
        assertThrows(IllegalArgumentException.class, () -> Rate.of(title, Rate.MAX_SCORE + 1, UserIdDummy.randomUserId()));
    }

    @Test
    void titleAndUserIdMustNotBeNull() {
        assertThrows(NullPointerException.class, () -> Rate.of(null, Rate.MAX_SCORE, null));
    }

    @Test
    void ratingOf5IsValid() {
        final Rate rate = Rate.of(title, 5, UserId.of("aUsername"));

        assertEquals("Rating{id='" + rate.id.toString() + "', " +
                "title='" + title + "', " +
                "score=5, userId=UserId{value='aUsername'}}", rate.toString());
    }

    @Test
    void ratingOf1IsValid() {
        final Rate rate = Rate.of(title, 1, UserId.of("aUsername"));

        assertEquals("Rating{id='" + rate.id.toString() + "', " +
                "title='" + title + "', " +
                "score=1, " +
                "userId=UserId{value='aUsername'}}", rate.toString());
    }
}
