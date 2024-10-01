package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.Entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Order object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Order object) {
        return false;
    }

    @Override
    public boolean delete(Order object) {
        return false;
    }

    @Override
    public Order findById(String id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }
}
