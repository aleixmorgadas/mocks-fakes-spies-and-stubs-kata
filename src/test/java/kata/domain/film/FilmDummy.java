package kata.domain.film;

import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FilmDummy {

    public static FilmBuilder randomFilm() {
        return create()
                .withTitle(Faker.instance().starTrek().character())
                .withCategories(Arrays.asList(Faker.instance().beer().hop(), Faker.instance().beer().malt()))
                .withDuration(Duration.ofMinutes(Faker.instance().number().randomDigitNotZero()))
                .withReleaseDate(Faker.instance().number().randomDigitNotZero());
    }

    public static FilmBuilder create() {
        return new FilmBuilder();
    }

    static public class FilmBuilder {
        private String title;
        private Duration duration;
        private List<String> categories;
        private Integer releaseDate;

        FilmBuilder() {
        }

        public FilmBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public FilmBuilder withDuration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public FilmBuilder withCategories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        public FilmBuilder withReleaseDate(Integer releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Film build() {
            return new Film(title, duration, categories, releaseDate);
        }
    }
}
