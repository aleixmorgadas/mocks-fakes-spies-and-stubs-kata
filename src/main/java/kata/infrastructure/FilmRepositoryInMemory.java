package kata.infrastructure;

import kata.domain.film.Film;
import kata.domain.film.FilmRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FilmRepositoryInMemory implements FilmRepository {
    private final Map<String, Film> database;

    public FilmRepositoryInMemory(final Map<String, Film> database) {
        this.database = database;
    }

    public FilmRepositoryInMemory() {
        database = new HashMap<>();
    }

    @Override
    public void save(String id, Film film) {
        database.put(id, film);
    }

    @Override
    public Optional<Film> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }
}
