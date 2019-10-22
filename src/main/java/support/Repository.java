package support;

import java.util.Optional;

public interface Repository<ENTITY, ID> {

    void save(ID id, ENTITY entity);

    Optional<ENTITY> findById(ID id);
}
