package kata.domain.film;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static kata.domain.film.FilmDummy.randomFilm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilmServiceTest_Stub {
    private static Film randomFilm = randomFilm().build();

    @Test
    void shouldReturnAFilmAtRepository() {
        final FilmService filmService = new FilmService(new FilmRepositoryReturningValue_Stub());

        final String title = Faker.instance().funnyName().name();
        assertEquals(randomFilm, filmService.findById(title).get());
    }

    @Test
    void shouldReturnAnEmptyOptionalWhenItIsNotPresent() {
        final FilmService filmService = new FilmService(new FilmRepositoryReturningEmpty_Stub());

        final String title = Faker.instance().funnyName().name();
        assertFalse(filmService.findById(title).isPresent());
    }

    static class FilmRepositoryReturningValue_Stub implements FilmRepository {

        @Override
        public void save(String title, Film film) {

        }

        @Override
        public Optional<Film> findById(String title) {
            return Optional.of(randomFilm);
        }
    }

    static class FilmRepositoryReturningEmpty_Stub implements FilmRepository {

        @Override
        public void save(String title, Film film) {

        }

        @Override
        public Optional<Film> findById(String title) {
            return Optional.empty();
        }
    }
}
