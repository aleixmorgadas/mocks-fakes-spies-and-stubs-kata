package kata.domain.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static kata.domain.film.FilmDummy.randomFilm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FilmServiceTest_Mock {
    final static private String title = "el viaje de chihiro";
    final static private Film film = randomFilm().withTitle(title).build();

    private FilmService filmService;

    @BeforeEach
    void setup() {
        filmService = new FilmService(new FilmRepository_Mock());
    }

    @Test
    void shouldReturnAFilmAtRepository() {
        final Film film = filmService.findById(title).get();

        assertEquals(FilmServiceTest_Mock.film, film);
    }

    @Test
    void shouldReturnAnEmptyOptionalWhenItIsNotPresent() {
        final String title = "Title of a film that is not present";

        assertFalse(filmService.findById(title).isPresent());
    }

    static class FilmRepository_Mock implements FilmRepository {

        @Override
        public void save(String title, Film film) {

        }

        @Override
        public Optional<Film> findById(String title) {
            if (title.equals(FilmServiceTest_Mock.title)) {
                return Optional.of(FilmServiceTest_Mock.film);
            }
            return Optional.empty();
        }
    }
}
