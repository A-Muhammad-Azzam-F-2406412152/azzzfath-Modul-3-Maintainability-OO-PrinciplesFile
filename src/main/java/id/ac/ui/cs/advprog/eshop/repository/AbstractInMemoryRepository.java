package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class AbstractInMemoryRepository<T> implements BaseRepository<T, String> {
    protected List<T> data = new ArrayList<>();

    protected abstract String getEntityId(T entity);
    protected abstract void setEntityId(T entity, String id);

    @Override
    public T create(T entity) {
        if (getEntityId(entity) == null) {
            setEntityId(entity, UUID.randomUUID().toString());
        }
        data.add(entity);
        return entity;
    }

    @Override
    public Iterator<T> findAll() {
        return data.iterator();
    }

    @Override
    public T findById(String id) {
        for (T entity : data) {
            if (getEntityId(entity).equals(id)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public T update(String id, T updatedEntity) {
        for (int i = 0; i < data.size(); i++) {
            T entity = data.get(i);
            if (getEntityId(entity).equals(id)) {
                setEntityId(updatedEntity, id);
                data.set(i, updatedEntity);
                return updatedEntity;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        data.removeIf(entity -> getEntityId(entity).equals(id));
    }
}