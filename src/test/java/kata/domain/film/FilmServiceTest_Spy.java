package kata.domain.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static kata.domain.film.FilmRandomGenerator.randomFilm;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FilmServiceTest_Spy {
    private FilmRepository filmRepositoryMock;
    private FilmService filmService;

    @BeforeEach
    void setup() {
        filmRepositoryMock = Mockito.mock(FilmRepository.class);
        filmService = new FilmService(filmRepositoryMock);
    }

    @Test
    void shouldSaveFilmToRepository() {
        final String title = "el viaje de chihiro";
        final Film film = randomFilm().withTitle(title).build();

        filmService.save(film);

        verify(filmRepositoryMock, times(1)).save(title, film);
    }
}
