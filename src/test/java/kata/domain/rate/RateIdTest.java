package kata.domain.rate;

import kata.domain.user.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateIdTest {

    @Test
    void ratingIdIsComposedOfTitleAndUserId() {
        final String title = "aTitle";
        final UserId userId = UserId.of("aUserId");

        final RateId rateId = RateId.of(title, userId);

        assertEquals("RatingId{title='aTitle', userId=UserId{value='aUserId'}, value='aTitle--aUserId'}", rateId.toString());
    }
}
