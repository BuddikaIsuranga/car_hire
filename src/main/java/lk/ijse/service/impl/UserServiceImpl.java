package lk.ijse.service.impl;

import lk.ijse.Entity.UserEntity;
import lk.ijse.dao.DaoFactory.DaoType;
import lk.ijse.dao.DaoFactory.FactoryDao;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.dto.UserDto;
import lk.ijse.service.custom.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = FactoryDao.getDao(DaoType.USER);


    @Override
    public void save(UserDto userDto) {
        UserEntity userEntity = new UserEntity(
                userDto.getUserId(),
                userDto.getRole(),
                userDto.getUserName(),
                userDto.getLName(),
                userDto.getFName(),
                userDto.getPassword());

        userDao.save(userEntity);
    }

    @Override
    public void update(UserDto userDto) {
        UserEntity userEntity = new UserEntity(
                userDto.getUserId(),
                userDto.getFName(),
                userDto.getLName(),
                userDto.getUserName(),
                userDto.getRole(),
                userDto.getPassword());

        userDao.update(userEntity);
    }

    @Override
    public UserDto searchUser(String userId) {

        UserEntity userEntity=userDao.search(userId);

        return new UserDto(userEntity.getUserId(),
                userEntity.getFName(),
                userEntity.getLName(),
                userEntity.getUserName(),
                userEntity.getRole(),
                userEntity.getPassword());
    }

    @Override
    public void delete(UserDto userDto) {

        UserEntity userEntity = new UserEntity(
                userDto.getUserId(),
                userDto.getFName(),
                userDto.getLName(),
                userDto.getUserName(),
                userDto.getRole(),
                userDto.getPassword());

        userDao.delete(userEntity);

    }
}
