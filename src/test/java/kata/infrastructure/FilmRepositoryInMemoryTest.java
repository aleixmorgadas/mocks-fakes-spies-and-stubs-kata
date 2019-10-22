package kata.infrastructure;

import com.github.javafaker.Faker;
import kata.domain.film.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kata.domain.film.FilmDummy.randomFilm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FilmRepositoryInMemoryTest {
    private FilmRepositoryInMemory filmRepository;

    @BeforeEach
    void setup() {
        filmRepository = new FilmRepositoryInMemory();
    }

    @Test
    void storesById() {
        final String title = "The Imitation Game";
        final Film film = randomFilm().withTitle(title).build();

        filmRepository.save(title, film);

        assertEquals(film, filmRepository.findById(title).get());
    }

    @Test
    void returnsEmptyOptionalWhenNoFilmIsPresentGivenId() {
        assertFalse(filmRepository.findById(Faker.instance().idNumber().valid()).isPresent());
    }
}
