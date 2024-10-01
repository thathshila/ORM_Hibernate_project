package lk.ijse.Dao;

import java.util.List;

public interface CrudDao<T> extends SuperDao {
    public boolean save(T object);
    public boolean update(T object);
    public boolean delete(Long id);
    public T findById(String id);
    public List<T> findAll();
}
