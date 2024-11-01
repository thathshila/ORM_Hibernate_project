package lk.ijse.Bo.Custom.Impl;

import javafx.scene.control.Alert;
import lk.ijse.Bo.Custom.OrderBo;
import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Dao.Custom.CustomerDao;
import lk.ijse.Dao.Custom.ItemDao;
import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.Dao.Custom.OrderDetailDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Dto.OrderDto;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;
import lk.ijse.Entity.Order;
import lk.ijse.Entity.OrderDetail;
import lk.ijse.Entity.Tm.OrderTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao = (OrderDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDER);
    CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.CUSTOMER);
    ItemDao itemDao = (ItemDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ITEM);
    OrderDetailDao orderDetailDao = (OrderDetailDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDER_DETAIL);
    @Override
    public boolean save(OrderDto orderDto) {
        //   return orderDao.save(new Order(order.getOrderId(),order.getCustomerId(),order.getItemCode(),order.getItemName(),order.getQuantity(),order.getPrice()));
        return orderDao.save(new Order(
                0L,
                orderDto.getTotal(),
                new Date(),
                orderDto.getCustomer(),
                null
        ));
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
