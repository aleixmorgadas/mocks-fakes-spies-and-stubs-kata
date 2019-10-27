package kata.domain.rate;

import kata.domain.film.Film;
import kata.domain.film.FilmService;
import kata.domain.user.UserId;
import kata.domain.user.UserIdDummy;
import kata.support.RateRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static kata.domain.film.FilmDummy.randomFilm;
import static kata.domain.rate.RateDummy.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RateServiceTest_Fake {
    private RateRepository repository;
    private FilmService filmService;
    private RateService rateService;
    private LikedNotifier likedNotifier;

    @BeforeEach
    public void setup() {
        repository = new RateRepositoryInMemory();
        filmService = Mockito.mock(FilmService.class);
        likedNotifier = Mockito.mock(LikedNotifier.class);
        rateService = new RateService(repository, filmService, likedNotifier);
    }

    @Test
    void shouldSaveInTheRepository() {
        final Rate rate = Rate.of("aTitle", 4, UserIdDummy.randomUserId());

        // Exercise
        rateService.save(rate);

        // Verify State
        assertTrue(repository.findById(rate.id).isPresent());
    }

    @Test
    void shouldReceiveFromRepository() {
        final Rate rate = Rate.of("aTitle", 4, UserIdDummy.randomUserId());
        repository.save(rate.id, rate);

        // Exercise
        final Optional<Rate> ratingFromRepo = rateService.findById(rate.id);

        // Verify State
        assertTrue(ratingFromRepo.isPresent());
        assertEquals(rate, ratingFromRepo.get());
    }

    @Test
    void shouldReturnRatesMadeByAUser() {
        final UserId userId = UserId.of("aUser");
        final Rate rateOneByUser = randomRate().withUserId(userId).build();
        final Rate rateTwoByUser = randomRate().withUserId(userId).build();

        final List<Rate> allRates = randomListOfRatesOfSize(10);
        allRates.add(rateOneByUser);
        allRates.add(rateTwoByUser);

        saveRatesIntoService(allRates);

        // Exercise
        final List<Rate> ratedByUser = rateService.findByUser(userId);

        // Verify State
        assertTrue(ratedByUser.contains(rateOneByUser));
        assertTrue(ratedByUser.contains(rateTwoByUser));
    }

    @Test
    void shouldReturnTheListOfRatesByAUserForAFilmThatWasProducedAtYearOrMoreRecent() {
        final UserId userId = UserId.of("aUser");
        final int productionYear = 2000;

        final String theLionKingTitle = "The Lion King";
        final Film theLionKingMovieAsOldFilm = randomFilm()
                .withTitle(theLionKingTitle)
                .withReleaseDate(1994)
                .build();
        final String frozenTitle = "Frozen";
        final Film frozenMovieAsNewerFilm = randomFilm()
                .withTitle(frozenTitle)
                .withReleaseDate(2013)
                .build();
        final Rate rateOfFrozenByUser = randomRate()
                .withTitle(frozenTitle)
                .withUserId(userId)
                .build();
        final Rate rateOfTheLionKingByUser = randomRate()
                .withTitle(theLionKingTitle)
                .withUserId(userId)
                .build();
        final List<Rate> allRates = randomListOfRatesOfSize(10);
        allRates.add(rateOfFrozenByUser);
        allRates.add(rateOfTheLionKingByUser);

        saveRatesIntoService(allRates);
        doReturn(Optional.of(frozenMovieAsNewerFilm)).when(filmService).findById(frozenTitle);
        doReturn(Optional.of(theLionKingMovieAsOldFilm)).when(filmService).findById(theLionKingTitle);

        // Exercise
        final List<Rate> ratesByUserOfFilmsMadeAtYear2000OrMoreRecent = rateService
                .ratedByUserAtYearOrMoreRecent(userId, productionYear);

        // Verify State
        assertEquals(singletonList(rateOfFrozenByUser), ratesByUserOfFilmsMadeAtYear2000OrMoreRecent);
    }

    @Test
    void whenAFilmIsRatedMoreThan10Times_ItShouldNotifyOnceThatItHasBeenLikedBy10DifferentUsers() {
        final String title = "aTitle";
        final List<Rate> ratesForFilm = randomListOfRatesOfSizeForFilm(RateService.RATES_PER_FILM_FOR_NOTIFICATION, title);

        // Exercise
        ratesForFilm.forEach(rateService::save);

        verify(likedNotifier, times(1)).notifyForFilm(title);
    }

    private void saveRatesIntoService(List<Rate> rates) {
        rates.forEach(rate -> repository.save(rate.id, rate));
    }
}
