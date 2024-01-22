package lk.ijse.service.custom;

import lk.ijse.dto.CarDto;
import lk.ijse.service.SupperService;

import java.util.List;

public interface CarService extends SupperService {


    void save(CarDto carDto);

    void update(CarDto carDto);

    CarDto searchCar(String carId);

    void delete(CarDto carDto);

    List<CarDto>getAll();
}


