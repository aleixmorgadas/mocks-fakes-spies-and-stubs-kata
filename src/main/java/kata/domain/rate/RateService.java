package kata.domain.rate;

import kata.domain.user.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Rate> findByUser(UserId userId) {
        return repository.all().stream().filter(rate -> rate.by(userId)).collect(Collectors.toList());
    }
}
