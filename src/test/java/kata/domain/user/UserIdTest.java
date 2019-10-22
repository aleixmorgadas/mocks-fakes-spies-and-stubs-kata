package kata.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserIdTest {

    @Test
    void aUserIdIsAllStringsButNotEmpty() {
        final UserId userId = UserId.of("unicorn");

        assertEquals("UserId{value='unicorn'}", userId.toString());
    }

    @Test
    void anEmptyStringIsAnInvalidUserId() {
        final Throwable throwable = assertThrows(IllegalArgumentException.class, () -> UserId.of(""));
        assertEquals("userId must not be blank or empty", throwable.getMessage());
    }

    @Test
    void aBlankStringIsAnInvalidUserId() {
        final Throwable throwable = assertThrows(IllegalArgumentException.class, () -> UserId.of(" "));
        assertEquals("userId must not be blank or empty", throwable.getMessage());
    }

    @Test
    void aNullUserIdIsInvalid() {
        final Throwable throwable = assertThrows(NullPointerException.class, () -> UserId.of(null));
        assertEquals("userId must not be null", throwable.getMessage());
    }
}
