package lk.ijse.Bo.Custom.Impl;

import lk.ijse.Bo.Custom.CustomerBo;
import lk.ijse.Dao.Custom.CustomerDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.CUSTOMER);

    @Override
    public boolean save(CustomerDto customer) {
        Customer customer1 = new Customer(1L, customer.getName(), customer.getAddress(), customer.getContact());
        return customerDao.save(customer1);

    }
    @Override
    public boolean update(Long id, CustomerDto customer) {
        Customer customer1 = new Customer(id, customer.getName(), customer.getAddress(), customer.getContact());
        return customerDao.update(customer1);
    }

    @Override
    public boolean delete(Long id) {
        return customerDao.delete(id);
    }

    @Override
    public CustomerDto getCustomer(Long id) {
//       Customer customer = customerDao.findById(id);
//       CustomerDto customerDto = new CustomerDto();
//       customerDto.setId(customer.getId());
//       customerDto.setName(customer.getName());
//       customerDto.setAddress(customer.getAddress());
//       customerDto.setContact(customer.getContact());
//       return customerDto;
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customerList = new ArrayList<>();
        List<Customer> all = customerDao.findAll();
        for (Customer customer : all) {
            customerList.add(new Customer(customer.getId(),customer.getName(), customer.getAddress(), customer.getContact()));
        }
        return customerList;
    }
}
