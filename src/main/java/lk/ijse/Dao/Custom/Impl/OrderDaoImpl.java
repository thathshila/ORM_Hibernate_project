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
    public boolean delete(Long code) {
        return false;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Long getCurrentId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Long nextId;

        Object order = session.createQuery("SELECT O.orderId FROM Order O ORDER BY O.orderId DESC LIMIT 1").uniqueResult();
        if (order != null) {
            String orderId = order.toString();
            String prefix = "O";
            String idWithoutPrefix = orderId.replace(prefix, "");

            Long idNum = Long.parseLong(idWithoutPrefix);
            nextId = idNum + 1;
        } else {
            // Initial ID if no existing records
            nextId = 1L;
        }

        transaction.commit();
        session.close();

        return nextId;
    }
}