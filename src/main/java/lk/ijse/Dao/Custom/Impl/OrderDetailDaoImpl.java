package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Dao.Custom.OrderDetailDao;
import lk.ijse.Entity.OrderDetail;
import org.hibernate.Session;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {


    @Override
    public boolean save(OrderDetail object) {
        return false;
    }

    @Override
    public boolean update(OrderDetail object) {
        return false;
    }

    @Override
    public boolean delete(Long code) {
        return false;
    }

    @Override
    public OrderDetail findById(Long id) {
        return null;
    }

    @Override
    public List<OrderDetail> findAll() {
        return List.of();
    }
}
