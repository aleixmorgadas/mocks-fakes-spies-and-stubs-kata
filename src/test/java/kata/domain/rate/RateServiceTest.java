package kata.domain.rate;

import kata.domain.user.UserIdDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class RateServiceTest {
    private RateRepository repository;
    private RateService rateService;

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
}
