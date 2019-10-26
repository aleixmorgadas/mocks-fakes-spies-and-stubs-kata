package kata.domain.rate;

import java.util.Optional;

public class RateService {
    private final RateRepository repository;

    public RateService(RateRepository repository) {
        this.repository = repository;
    }

    public void save(Rate rate) {
        repository.save(rate.id, rate);
    }

    public Optional<Rate> findById(RateId id) {
        return repository.findById(id);
    }
}
