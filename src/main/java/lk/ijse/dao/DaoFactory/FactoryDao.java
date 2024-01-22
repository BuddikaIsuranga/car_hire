package lk.ijse.dao.DaoFactory;

import lk.ijse.dao.impl.*;

public class FactoryDao {
    public static <T>T getDao(DaoType type) {
        switch (type) {
            case CATERGORY:
                return (T) new CatergoryDaoImpl();
            case CAR:
                return (T) new CarDaoImpl();
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case USER:
                return (T) new UserDaoImpl();
            case RENT:
                return (T) new RentDaoImpl();
            default:
                return null;
        }
    }
}
