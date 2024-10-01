package lk.ijse.Bo.Custom;

import lk.ijse.Bo.SuperBo;
import lk.ijse.Dto.CustomerDto;


import java.util.List;

public interface CustomerBo extends SuperBo {
    public boolean save(CustomerDto customer);
    public boolean update(CustomerDto customer);
    public boolean delete(CustomerDto customer);
    public CustomerDto getCustomer(Long id);
    public List<CustomerDto> getCustomers();
}
