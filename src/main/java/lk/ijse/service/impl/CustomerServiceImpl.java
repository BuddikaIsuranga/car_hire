package lk.ijse.service.impl;

import lk.ijse.Entity.CatergoryEntity;
import lk.ijse.Entity.CustomerEntity;
import lk.ijse.dao.DaoFactory.DaoType;
import lk.ijse.dao.DaoFactory.FactoryDao;
import lk.ijse.dao.custom.CarDao;
import lk.ijse.dao.custom.CatergoryDao;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.impl.CustomerDaoImpl;
import lk.ijse.dto.CategoryDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.service.custom.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao= FactoryDao.getDao(DaoType.CUSTOMER);


    @Override
    public void update(CustomerDto customerDto) {
        customerDao.update(toCustomerEntity(customerDto));


    }

    private CustomerEntity toCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustId(customerDto.getCustomerId());
        customerEntity.setUserName(customerDto.getUserName());
        customerEntity.setFName(customerDto.getFName());
        customerEntity.setLName(customerDto.getLName());
        customerEntity.setStreet(customerDto.getStreet());
        customerEntity.setCity(customerDto.getCity());
        customerEntity.setDistriict(customerDto.getDistrict());
        customerEntity.setPostCode(customerDto.getPostCode());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setContact(customerDto.getContact());
        return customerEntity;
    }

    @Override
    public void save(CustomerDto customerDto) {
        customerDao.save(toCustomerEntity(customerDto));
    }

    @Override
    public CustomerDto serach(String customerId) {
        CustomerEntity customerEntity = customerDao.search(customerId);
        return (customerEntity != null) ? toCustomerDto(customerEntity) : null;
    }

    private CustomerDto toCustomerDto(CustomerEntity e) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(e.getCustId());
        customerDto.setUserName(e.getUserName());
        customerDto.setFName(e.getFName());
        customerDto.setLName(e.getLName());
        customerDto.setStreet(e.getStreet());
        customerDto.setCity(e.getCity());
        customerDto.setDistrict(e.getDistriict());
        customerDto.setPostCode(e.getPostCode());
        customerDto.setEmail(e.getEmail());
        customerDto.setContact(e.getContact());
        return customerDto;
    }



    @Override
    public void delete(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustId(customerDto.getCustomerId());
        customerDao.delete(customerEntity);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerEntity> getAll = customerDao.getAll();
        return getAll.stream().map(this::toCustomerDto).collect(Collectors.toList());
    }
}
