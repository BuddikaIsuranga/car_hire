package lk.ijse.service.impl;

import lk.ijse.Entity.CarEntity;
import lk.ijse.Entity.CatergoryEntity;
import lk.ijse.dao.DaoFactory.DaoType;
import lk.ijse.dao.DaoFactory.FactoryDao;
import lk.ijse.dao.custom.CarDao;
import lk.ijse.dao.custom.CatergoryDao;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.CategoryDto;
import lk.ijse.service.custom.CarService;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService{


    private final CarDao carDao = FactoryDao.getDao(DaoType.CAR);
    private final CatergoryDao categoryDao = FactoryDao.getDao(DaoType.CATERGORY);



    @Override
    public void save(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarId(carDto.getCarId());
        carEntity.setCarNumber(carDto.getCarNumber());
        carEntity.setCarBrand(carDto.getCarBrand());
        carEntity.setCarModel(carDto.getCarModel());
        carEntity.setCarYear(carDto.getCarYear());
        carEntity.setCarRate(carDto.getCarRate());
        carEntity.setIsCarAvailable(carDto.getIsCarAvailable());
        carEntity.setCategory(
                categoryDao.search(carDto.getCategoryDto().getCategoryid())
        );
        carDao.save(carEntity);
    }

    @Override
    public void update(CarDto carDto) {

    }

    @Override
    public CarDto searchCar(String carId) {
        CarEntity carsEntity = carDao.search(carId);
        return carsEntity != null ? toCarDto(carsEntity) : null;
    }

    private CarDto toCarDto(CarEntity carEntity) {

        return new CarDto(
                new CategoryDto(
                        carEntity.getCategory().getCategoryid(),
                        carEntity.getCategory().getCategoryname()),
                carEntity.getCarId(),
                carEntity.getCarNumber(),
                carEntity.getCarBrand(),
                carEntity.getCarModel(),
                carEntity.getCarYear(),
                carEntity.getCarRate(),
                carEntity.getIsCarAvailable()
        );




    }

    @Override
    public void delete(CarDto carDto) {
        carDao.delete(toCarEntity(searchCar(carDto.getCarId())));

    }

    private CarEntity toCarEntity(CarDto carDto) {
        return new CarEntity(
                new CatergoryEntity(
                        carDto.getCategoryDto().getCategoryid(),
                        carDto.getCategoryDto().getCategoryname()),
                carDto.getCarId(),
                carDto.getCarNumber(),
                carDto.getCarBrand(),
                carDto.getCarModel(),
                carDto.getCarYear(),
                carDto.getCarRate(),
                carDto.getIsCarAvailable(),
                null
        );
    }

    @Override
    public List<CarDto> getAll() {
        List<CarEntity> all = carDao.getAll();
        return all != null ? all.stream().map(this::toCarDto).collect(Collectors.toList()) : null;
    }


}
