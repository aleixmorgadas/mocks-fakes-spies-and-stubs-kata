package kata.domain.ratings;

import kata.domain.user.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingIdTest {

    @Test
    void ratingIdIsComposedOfTitleAndUserId() {
        final String title = "aTitle";
        final UserId userId = UserId.of("aUserId");

        final RatingId ratingId = RatingId.of(title, userId);

        assertEquals("RatingId{title='aTitle', userId=UserId{value='aUserId'}, value='aTitle--aUserId'}", ratingId.toString());
    }
}
