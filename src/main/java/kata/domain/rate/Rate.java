package kata.domain.rate;

import kata.domain.user.UserId;

import java.util.Objects;

public class Rate {
    static final int MIN_SCORE = 1;
    static final int MAX_SCORE = 5;

    public final RateId id;
    public final String title;
    public final int score;
    public final UserId userId;

    Rate(String title, int score, UserId userId) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(userId);
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("score must be between 1 or 5, both included");
        }
        this.id = RateId.of(title, userId);
        this.title = title;
        this.score = score;
        this.userId = userId;
    }

    public static Rate of(String title, int score, UserId userId) {
        return new Rate(title, score, userId);
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
