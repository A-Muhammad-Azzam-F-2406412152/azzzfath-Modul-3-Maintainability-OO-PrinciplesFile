package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.BaseRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractBaseServiceImpl<T> implements BaseService<T, String> {

    protected abstract BaseRepository<T, String> getRepository();

    @Override
    public T create(T entity) {
        return getRepository().create(entity);
    }

    @Override
    public List<T> findAll() {
        Iterator<T> iterator = getRepository().findAll();
        List<T> allEntities = new ArrayList<>();
        iterator.forEachRemaining(allEntities::add);
        return allEntities;
    }

    @Override
    public T findById(String id) {
        return getRepository().findById(id);
    }

    @Override
    public T update(String id, T entity) {
        return getRepository().update(id, entity);
    }

    @Override
    public void deleteById(String id) {
        getRepository().delete(id);
    }
}