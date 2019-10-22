package kata.domain.ratings;

public class Rating {
    static final int MIN_SCORE = 1;
    static final int MAX_SCORE = 5;

    public final String title;
    public final Integer score;

    Rating(String title, Integer score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("score must be between 1 or 5, both included");
        }
        this.title = title;
        this.score = score;
    }

    public static Rating of(String title, Integer score) {
        return new Rating(title, score);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "title='" + title + '\'' +
                ", score=" + score +
                '}';
    }
}
