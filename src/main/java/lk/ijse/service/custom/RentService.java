package lk.ijse.service.custom;

import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RentDto;
import lk.ijse.service.SupperService;

import java.util.List;

public interface RentService extends SupperService {


    void save(RentDto rentDto);

    List<RentDto> getAll();
}
