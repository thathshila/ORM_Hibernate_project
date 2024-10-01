package lk.ijse.Bo.Custom;

import lk.ijse.Bo.SuperBo;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Entity.Customer;


import java.util.List;

public interface CustomerBo extends SuperBo {
    public boolean save(CustomerDto customer);
    public boolean update(Long id,CustomerDto customer);
    public boolean delete(Long id);
    public CustomerDto getCustomer(Long id);
    public List<Customer> getCustomers();
}
