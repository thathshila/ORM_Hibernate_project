package lk.ijse.Bo.Custom.Impl;

import lk.ijse.Bo.Custom.OrderBo;
import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Entity.Order;

import java.util.List;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao = (OrderDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDER);

    @Override
    public boolean save(Order order) {
     //   return orderDao.save(new Order(order.getOrderId(),order.getCustomerId(),order.getItemCode(),order.getItemName(),order.getQuantity(),order.getPrice()));
    return false;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public Order getOrder(String id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }
}
