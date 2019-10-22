package kata.domain.film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static kata.domain.film.FilmRandomGenerator.randomFilm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

class FilmServiceTest_Mock {
    private FilmRepository filmRepositoryMock;
    private FilmService filmService;

    @BeforeEach
    void setup() {
        filmRepositoryMock = Mockito.mock(FilmRepository.class);
        filmService = new FilmService(filmRepositoryMock);
    }

    @Test
    void shouldReturnAFilmAtRepository() {
        final String title = "el viaje de chihiro";
        final Film film = randomFilm().withTitle(title).build();

        doReturn(Optional.of(film)).when(filmRepositoryMock).findById(title);

        assertEquals(film, filmService.findById(title).get());
    }

    @Test
    void shouldReturnAnEmptyOptionalWhenItIsNotPresent() {
        doReturn(Optional.empty()).when(filmRepositoryMock).findById(anyString());

        assertFalse(filmService.findById("random-title").isPresent());
    }
}
