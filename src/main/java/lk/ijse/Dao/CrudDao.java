package lk.ijse.Dao;

import java.util.List;

public interface CrudDao<T> extends SuperDao {
    public boolean save(T object);
    public boolean update(T object);
    public boolean delete(Long code);
    public T findById(Long id);
    public List<T> findAll();
}
