package kata.domain.ratings;

import kata.domain.user.UserId;

import java.util.Objects;

public class Rating {
    static final int MIN_SCORE = 1;
    static final int MAX_SCORE = 5;

    public final String id;
    public final String title;
    public final int score;
    public final UserId userId;

    Rating(String title, int score, UserId userId) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(userId);
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("score must be between 1 or 5, both included");
        }
        this.id = title + "-" + userId.value;
        this.title = title;
        this.score = score;
        this.userId = userId;
    }

    public static Rating of(String title, int score, UserId userId) {
        return new Rating(title, score, userId);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", userId=" + userId +
                '}';
    }
}
