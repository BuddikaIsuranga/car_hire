package lk.ijse.service.custom;

import lk.ijse.dto.UserDto;
import lk.ijse.service.SupperService;

public interface UserService extends SupperService {

    void save(UserDto userDto);

    void update(UserDto userDto);

    UserDto searchUser(String userId);

    void delete(UserDto userDto);
}
