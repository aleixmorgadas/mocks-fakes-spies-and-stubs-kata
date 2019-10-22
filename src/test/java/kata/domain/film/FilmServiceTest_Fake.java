package kata.domain.film;

import kata.infrastructure.FilmRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static kata.domain.film.FilmDummy.randomFilm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilmServiceTest_Fake {
    private FilmRepositoryInMemory repository;
    private FilmService filmService;

    @BeforeEach
    void setup() {
        repository = new FilmRepositoryInMemory();
        filmService = new FilmService(repository);
    }

    @Test
    void shouldReturnAFilmAtRepository() {
        final String title = "el viaje de chihiro";
        final Film film = randomFilm().withTitle(title).build();
        repository.save(title, film);

        final Optional<Film> filmFoundById = filmService.findById(title);

        assertEquals(film, filmFoundById.get());
    }

    @Test
    void shouldReturnAnEmptyOptionalWhenItIsNotPresent() {
        assertFalse(filmService.findById("random-title").isPresent());
    }
}
