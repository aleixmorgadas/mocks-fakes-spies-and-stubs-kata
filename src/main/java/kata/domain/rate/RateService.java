package kata.domain.rate;

import kata.domain.film.Film;
import kata.domain.film.FilmService;
import kata.domain.user.UserId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RateService {
    private final RateRepository repository;
    private final FilmService filmService;

    public RateService(RateRepository repository, FilmService filmService) {
        this.repository = repository;
        this.filmService = filmService;
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

    public List<Rate> ratedByUserAtYearOrMoreRecent(UserId userId, int productionYear) {
        return findByUser(userId).stream()
                .filter(rate -> {
                    final Optional<Film> filmOptional = filmService.findById(rate.title);
                    return filmOptional.isPresent() && filmOptional.get().releaseDate >= productionYear;
                })
                .collect(Collectors.toList());
    }
}
