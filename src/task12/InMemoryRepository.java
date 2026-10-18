package task12;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class InMemoryRepository<ID, T> implements Repository<ID, T> {

    private final Map<ID, T> storage = new LinkedHashMap<>();
    private final Function<T, ID> idOf;

    public InMemoryRepository(Function<T, ID> idOf) {
        this.idOf = Objects.requireNonNull(idOf, "idOf");
    }

    @Override
    public T save(T entity) {
        Objects.requireNonNull(entity, "entity");
        ID id = Objects.requireNonNull(idOf.apply(entity), "entity id");
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        Objects.requireNonNull(id, "id");
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public boolean deleteById(ID id) {
        Objects.requireNonNull(id, "id");
        return storage.remove(id) != null;
    }
}
