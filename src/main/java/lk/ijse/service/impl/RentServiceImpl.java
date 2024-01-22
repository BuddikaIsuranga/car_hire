package lk.ijse.service.impl;


import lk.ijse.Entity.CarEntity;
import lk.ijse.Entity.RentEntity;
import lk.ijse.dao.DaoFactory.DaoType;
import lk.ijse.dao.DaoFactory.FactoryDao;
import lk.ijse.dao.custom.CarDao;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dao.custom.RentDao;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RentDto;
import lk.ijse.service.custom.RentService;

import java.util.ArrayList;
import java.util.List;

public class RentServiceImpl implements RentService {

    private final RentDao rentDao = FactoryDao.getDao(DaoType.RENT);
    private final CustomerDao customerDao = FactoryDao.getDao(DaoType.CUSTOMER);
    private final CarDao carDao = FactoryDao.getDao(DaoType.CAR);


    @Override
    public void save(RentDto rentDto) {

        CarEntity searchedCar = carDao.search(rentDto.getCarDto().getCarId());
        RentEntity rentEntity = new RentEntity(
                rentDto.getRentId(),
                rentDto.getStartDate(),
                rentDto.getEndDate(),
                rentDto.getPayment(),
                rentDto.getTotal(),
                rentDto.getStatus(),
                customerDao.search(rentDto.getCustomerDto().getCustomerId()),
                searchedCar
        );
        rentDao.save(rentEntity);


    }

    @Override
    public List<RentDto> getAll() {

        List<RentEntity> getAll = rentDao.getAll();
        if (getAll != null) {
            return getAll.stream().map(
                    rentEntity -> new RentDto(
                            rentEntity.getRentId(),
                            rentEntity.getStartDate(),
                            rentEntity.getEndDate(),
                            rentEntity.getPayment(),
                            rentEntity.getTotal(),
                            rentEntity.getStatus(),
                            new CustomerDto(
                                    rentEntity.getCustomerEntity().getId(),
                                    rentEntity.getCustomerEntity().getUserName(),
                                    rentEntity.getCustomerEntity().getFName(),
                                    rentEntity.getCustomerEntity().getLName(),
                                    rentEntity.getCustomerEntity().getStreet(),
                                    rentEntity.getCustomerEntity().getCity(),
                                    rentEntity.getCustomerEntity().getDistriict(),
                                    rentEntity.getCustomerEntity().getPostCode(),
                                    rentEntity.getCustomerEntity().getEmail(),
                                    rentEntity.getCustomerEntity().getContact(),
                                    null
                            ),
                            new CarDto(
                                    null,
                                    rentEntity.getCarEntity().getCarId(),
                                    rentEntity.getCarEntity().getCarNumber(),
                                    rentEntity.getCarEntity().getCarBrand(),
                                    rentEntity.getCarEntity().getCarModel(),
                                    rentEntity.getCarEntity().getCarYear(),
                                    rentEntity.getCarEntity().getCarRate(),
                                    rentEntity.getCarEntity().getIsCarAvailable()
                            )
                    )
            ).toList();
        }
        return new ArrayList<>();

    }
}
