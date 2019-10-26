package kata.domain.rate;

import kata.domain.user.UserId;

public class RateId {
    public final String value;
    private final String title;
    private final UserId userId;

    RateId(String title, UserId userId) {
        this.title = title;
        this.userId = userId;
        this.value = title + "--" + userId.value;
    }

    public static RateId of(String title, UserId userId) {
        return new RateId(title, userId);
    }

    @Override
    public String toString() {
        return "RatingId{" +
                "title='" + title + '\'' +
                ", userId=" + userId +
                ", value='" + value + '\'' +
                '}';
    }
}
