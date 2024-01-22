package lk.ijse.service.factory;

import lk.ijse.controller.UserFormController;
import lk.ijse.service.impl.*;

public class ServiceFactory {

    public static <T>T getService(ServiceType type) {
        switch (type) {
            case CATERGORY:
                return (T) new CatergoryServiceImpl();
            case CAR:
                return (T) new CarServiceImpl();
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case USER:
                return (T) new UserServiceImpl();
            case RENT:
                return (T) new RentServiceImpl();

            default:
                return null;
        }
    }
}
