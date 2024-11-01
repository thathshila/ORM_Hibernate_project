package lk.ijse.Dao.Custom;

import lk.ijse.Dao.CrudDao;
import lk.ijse.Entity.Customer;

import java.util.List;

public interface CustomerDao extends CrudDao<Customer> {
    List<Long> getCustomerId();

    Customer getCustomerById(Long id);
}
