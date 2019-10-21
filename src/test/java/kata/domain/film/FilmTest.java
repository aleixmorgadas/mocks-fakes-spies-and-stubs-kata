package kata.domain.film;

import org.junit.jupiter.api.Test;

import static java.time.Duration.ofHours;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmTest {

    @Test
    void shouldBeCreated() {
        final Film film = Film.of("Interstellar",
                ofHours(2).plusMinutes(49),
                asList("Adventure", "Drama", "Sci-Fi"),
                2014);

        assertEquals("Film{title='Interstellar', duration=PT2H49M, categories=[Adventure, Drama, Sci-Fi], releaseDate=2014}",
                film.toString());
    }

    @Test
    void shouldNotAcceptNullValues() {
        assertThrows(NullPointerException.class, () -> Film.of(null, null, null, null));
    }

    @Test
    void titleMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Film.of("",
                ofHours(2).plusMinutes(49),
                asList("Adventure", "Drama", "Sci-Fi"),
                2014));
    }
}
