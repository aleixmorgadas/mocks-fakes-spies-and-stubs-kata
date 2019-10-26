package kata.domain.ratings;

import kata.domain.user.UserId;

public class RatingId {
    public final String value;
    private final String title;
    private final UserId userId;

    RatingId(String title, UserId userId) {
        this.title = title;
        this.userId = userId;
        this.value = title + "--" + userId.value;
    }

    public static RatingId of(String title, UserId userId) {
        return new RatingId(title, userId);
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
