package lk.ijse.dao.impl;

import lk.ijse.Entity.CarEntity;
import lk.ijse.Entity.RentEntity;
import lk.ijse.dao.custom.RentDao;
import lk.ijse.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RentDaoImpl implements RentDao {
    @Override
    public void save(RentEntity rentEntity) {
        try(Session session= HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            session.save(rentEntity);
            session.get(CarEntity.class,rentEntity.getCarEntity().getCarId()).setIsCarAvailable(false);
            transaction.commit();
        }


    }

    @Override
    public void update(RentEntity rentID) {

    }



    @Override
    public void delete(RentEntity rentEntity) {

    }

    @Override
    public RentEntity search(String id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        RentEntity rentEntity=session.get(RentEntity.class, id);
        transaction.commit();
        return rentEntity;

    }

    @Override
    public List<RentEntity> getAll() {

        Session session=HibernateUtil.getSessionFactory().openSession();
        Query query=session.createQuery("FROM RentEntity ");
       return query.list();


    }

    @Override
    public void updateRentStatusByRentID(String rentID) {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("UPDATE RentEntity SET status='complete' WHERE rentId=:rentId");
            query.setParameter("rentId",rentID);
            query.executeUpdate();
            transaction.commit();
        }
    }
    }

