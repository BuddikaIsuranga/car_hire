package lk.ijse.dao.impl;

import lk.ijse.Entity.CarEntity;
import lk.ijse.dao.custom.CarDao;
import lk.ijse.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarDaoImpl implements CarDao {
    @Override
    public void save(CarEntity carEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.persist(carEntity);
        transaction.commit();

    }

    @Override
    public void update(CarEntity carEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.update(carEntity);
        transaction.commit();

    }

    @Override
    public void delete(CarEntity carEntity) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(carEntity);
        transaction.commit();

    }

    @Override
    public CarEntity search(String id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        CarEntity carEntity=session.get(CarEntity.class, id);
        transaction.commit();

        return carEntity;
    }

    @Override
    public List<CarEntity> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return  session.createQuery("from CarEntity").list();
        }
    }


}
