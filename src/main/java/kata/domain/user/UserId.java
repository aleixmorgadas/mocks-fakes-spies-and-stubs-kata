package kata.domain.user;

import java.util.Objects;

public class UserId {
    public final String value;

    UserId(String value) {
        this.value = value;
    }

    public static UserId of(String value) {
        Objects.requireNonNull(value, "userId must not be null");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("userId must not be blank or empty");
        }
        return new UserId(value);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "value='" + value + '\'' +
                '}';
    }
}
