package kata.domain.rate;

import kata.domain.film.Film;
import kata.domain.film.FilmService;
import kata.domain.user.UserId;
import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static kata.domain.film.FilmDummy.randomFilm;
import static kata.domain.rate.RateDummy.createRate;
import static kata.domain.rate.RateDummy.randomListOfRatesOfSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class RateServiceTest {
    private RateRepository repository;
    private FilmService filmService;
    private RateService rateService;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(RateRepository.class);
        filmService = Mockito.mock(FilmService.class);
        rateService = new RateService(repository, filmService);
    }

    @Test
    void shouldSaveInTheRepository() {
        final Rate rate = Rate.of("aTitle", 4, UserIdDummy.randomUserId());

        rateService.save(rate);

        verify(repository).save(rate.id, rate);
    }

    @Test
    void shouldReceiveFromRepository() {
        final Rate rate = Rate.of("aTitle", 4, UserIdDummy.randomUserId());
        doReturn(Optional.of(rate)).when(repository).findById(any());

        final Optional<Rate> ratingFromRepo = rateService.findById(rate.id);

        assertTrue(ratingFromRepo.isPresent());
        assertEquals(rate, ratingFromRepo.get());
    }

    @Test
    void shouldReturnRatesMadeByAUser() {
        final UserId userId = UserId.of("aUser");
        final Rate rateOneByUser = createRate().withUserId(userId).build();
        final Rate rateTwoByUser = createRate().withUserId(userId).build();

        final List<Rate> allRates = randomListOfRatesOfSize(10);
        allRates.add(rateOneByUser);
        allRates.add(rateTwoByUser);

        doReturn(allRates).when(repository).all();

        final List<Rate> ratedByUser = rateService.findByUser(userId);

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
        final Rate rateOfFrozenByUser = createRate()
                .withTitle(frozenTitle)
                .withUserId(userId)
                .build();
        final Rate rateOfTheLionKingByUser = createRate()
                .withTitle(theLionKingTitle)
                .withUserId(userId)
                .build();
        final List<Rate> allRates = randomListOfRatesOfSize(10);
        allRates.add(rateOfFrozenByUser);
        allRates.add(rateOfTheLionKingByUser);

        doReturn(allRates).when(repository).all();
        doReturn(Optional.of(frozenMovieAsNewerFilm)).when(filmService).findById(frozenTitle);
        doReturn(Optional.of(theLionKingMovieAsOldFilm)).when(filmService).findById(theLionKingTitle);

        final List<Rate> ratesByUserOfFilmsMadeAtYear2000OrMoreRecent = rateService
                .ratedByUserAtYearOrMoreRecent(userId, productionYear);

        assertEquals(singletonList(rateOfFrozenByUser), ratesByUserOfFilmsMadeAtYear2000OrMoreRecent);
    }


}
