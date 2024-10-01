package lk.ijse.Bo.Custom;

import lk.ijse.Bo.SuperBo;
import lk.ijse.Entity.Order;

import java.util.List;

public interface OrderBo extends SuperBo {
    public boolean save(Order order);
    public boolean delete(Order order);
    public Order getOrder(String id);
    public List<Order> getAllOrders();
}
