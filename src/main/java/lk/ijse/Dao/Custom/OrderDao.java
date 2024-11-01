package lk.ijse.Dao.Custom;

import lk.ijse.Dao.CrudDao;
import lk.ijse.Entity.Order;
import lk.ijse.Entity.OrderDetail;
import org.hibernate.Session;

import java.util.List;

public interface OrderDao extends CrudDao<Order> {

    Long getCurrentId();

    Order getOrderById(Long id);

    void saveOrderDetails(List<OrderDetail> orderDetails);
}


