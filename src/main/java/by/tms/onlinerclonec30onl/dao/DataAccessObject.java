package by.tms.onlinerclonec30onl.dao;

import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T> {

    public void save(T entity);

    public void delete(T entity);

    public void deleteById(long id);

    public void update(long id, T entity);

    public List<T> findAll();

    public Optional<T> findByID(long id);
}