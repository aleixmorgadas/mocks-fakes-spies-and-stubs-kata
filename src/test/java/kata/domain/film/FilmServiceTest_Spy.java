package kata.domain.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static kata.domain.film.FilmDummy.randomFilm;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmServiceTest_Spy {
    private final static String title = "el viaje de chihiro";
    private final static Film film = randomFilm().withTitle(title).build();

    private FilmRepository_Spy repository;
    private FilmService filmService;

    @BeforeEach
    void setup() {
        repository = new FilmRepository_Spy();
        filmService = new FilmService(repository);
    }

    @Test
    void shouldSaveFilmToRepository() {
        filmService.save(film);

        assertTrue(repository.hasBeenCalledWithExpectedParams);
    }

    static class FilmRepository_Spy implements FilmRepository {
        boolean hasBeenCalledWithExpectedParams = false;

        @Override
        public void save(String title, Film film) {
            if (title.equals(FilmServiceTest_Spy.title) && film.equals(FilmServiceTest_Spy.film)) {
                hasBeenCalledWithExpectedParams = true;
            }
        }

        @Override
        public Optional<Film> findById(String s) {
            return Optional.empty();
        }
    }
}
