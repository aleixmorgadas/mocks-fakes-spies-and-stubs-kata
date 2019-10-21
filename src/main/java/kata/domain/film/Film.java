package kata.domain.film;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Film {
    public final String title;
    public final Duration duration;
    public final List<String> categories;
    public final Integer releaseDate;

    Film(String title, Duration duration, List<String> categories, Integer releaseDate) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(duration, "duration must not be null");
        Objects.requireNonNull(categories, "categories must not be null");
        Objects.requireNonNull(releaseDate, "releaseDate must not be null");
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title is empty");
        }
        this.title = title;
        this.duration = duration;
        this.categories = categories;
        this.releaseDate = releaseDate;
    }

    public static Film of(String title, Duration duration, List<String> categories, Integer releaseDate) {
        return new Film(title, duration, categories, releaseDate);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", categories=" + categories +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
