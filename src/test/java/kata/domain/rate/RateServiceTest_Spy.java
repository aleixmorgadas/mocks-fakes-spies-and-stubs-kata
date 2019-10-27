package kata.domain.rate;

import kata.domain.film.FilmService;
import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static kata.domain.rate.RateDummy.randomListOfRatesOfSizeForFilm;
import static kata.domain.rate.RateDummy.randomRate;
import static org.mockito.Mockito.*;

class RateServiceTest_Spy {
    private RateRepository repository;
    private FilmService filmService;
    private RateService rateService;
    private LikedNotifier likedNotifier;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(RateRepository.class);
        filmService = Mockito.mock(FilmService.class);
        likedNotifier = Mockito.mock(LikedNotifier.class);
        rateService = new RateService(repository, filmService, likedNotifier);
    }

    @Test
    void shouldSaveInTheRepository_usingAnSpy() {
        final Rate rate = Rate.of("aTitle", 4, UserIdDummy.randomUserId());

        // Exercise
        rateService.save(rate);

        // Verify expectations
        verify(repository).save(rate.id, rate);
    }

    @Test
    void whenAFilmIsRatedMoreThan10Times_ItShouldNotifyOnceThatItHasBeenLikedBy10DifferentUsers_usingAnSpy() {
        final String title = "aTitle";
        final List<Rate> ratesForFilm = randomListOfRatesOfSizeForFilm(RateService.RATES_PER_FILM_FOR_NOTIFICATION, title);

        // Setup
        doReturn(ratesForFilm).when(repository).ratesForFilm(title);

        // Exercise
        rateService.save(randomRate().withTitle(title).build());

        // Verify it has been called
        verify(likedNotifier, times(1)).notifyForFilm(title);
    }
}
