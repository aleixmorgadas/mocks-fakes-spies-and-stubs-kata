package kata.support;

import kata.domain.rate.Rate;
import kata.domain.rate.RateId;
import kata.domain.rate.RateRepository;

import java.util.*;

public class RateRepositoryInMemory implements RateRepository {
    private final Map<RateId, Rate> db = new HashMap<>();

    @Override
    public void save(RateId id, Rate rate) {
        db.put(id, rate);
    }

    @Override
    public Optional<Rate> findById(RateId id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<Rate> all() {
        return new ArrayList<>(db.values());
    }
}
