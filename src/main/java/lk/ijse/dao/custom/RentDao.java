package lk.ijse.dao.custom;

import lk.ijse.Entity.RentEntity;
import lk.ijse.dao.CrudDao;

public interface RentDao extends CrudDao<RentEntity,String> {
    void updateRentStatusByRentID(String rentID);
}
