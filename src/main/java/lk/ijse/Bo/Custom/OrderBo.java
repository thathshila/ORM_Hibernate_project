package lk.ijse.Bo.Custom;

import lk.ijse.Bo.SuperBo;
import lk.ijse.Dto.OrderDto;
import lk.ijse.Entity.Order;
import lk.ijse.Entity.Tm.OrderTm;

import java.util.List;

public interface OrderBo extends SuperBo {
    public boolean save(OrderDto orderDto);
    public boolean delete(Order order);
    public Order getOrder(String id);
    public List<Order> getAllOrders();
}
