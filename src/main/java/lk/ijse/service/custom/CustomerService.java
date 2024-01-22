package lk.ijse.service.custom;

import lk.ijse.dto.CustomerDto;
import lk.ijse.service.SupperService;

import java.util.List;

public interface CustomerService extends SupperService {


      void update(CustomerDto customerDto);

     void save(CustomerDto customerDto);

    CustomerDto serach(String customerId);

    void delete(CustomerDto customerDto);

    List<CustomerDto> getAll();
}
