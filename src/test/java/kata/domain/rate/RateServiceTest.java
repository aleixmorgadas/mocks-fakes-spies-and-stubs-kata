package kata.domain.rate;

import com.github.javafaker.Faker;
import kata.domain.user.UserId;
import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static kata.domain.rate.RateDummy.randomListOfRatesOfSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class RateServiceTest {
    private RateRepository repository;
    private RateService rateService;

    private Random random = new Random();

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(RateRepository.class);
        rateService = new RateService(repository);
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
        final Rate rateOneByUser = Rate.of(Faker.instance().funnyName().name(), random.nextInt(5) + 1, userId);
        final Rate rateTwoByUser = Rate.of(Faker.instance().funnyName().name(), random.nextInt(5) + 1, userId);

        final List<Rate> allRates = randomListOfRatesOfSize(10);
        allRates.add(rateOneByUser);
        allRates.add(rateTwoByUser);

        doReturn(allRates).when(repository).all();

        final List<Rate> ratedByUser = rateService.findByUser(userId);

        assertTrue(ratedByUser.contains(rateOneByUser));
        assertTrue(ratedByUser.contains(rateTwoByUser));
    }
}
