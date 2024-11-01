package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Order;
import lk.ijse.Entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

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

    @Override
    public Order getOrderById(Long id) {
        Session session = null;
        Transaction transaction = null;
        Order order = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<Order> query = session.createNativeQuery("SELECT * FROM orders WHERE orderId = :id", Order.class);
            query.setParameter("id", id);

            order = query.uniqueResult(); // Execute query and set the result to customer

            transaction.commit(); // Commit the transaction if successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction if an error occurs
            }
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed
            }
        }

        return order;
    }

    @Override
    public void saveOrderDetails(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(orderDetail);
            transaction.commit();
            session.close();
        }
    }
}
