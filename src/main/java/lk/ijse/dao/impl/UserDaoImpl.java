package lk.ijse.dao.impl;

import jakarta.persistence.Id;
import lk.ijse.Entity.CarEntity;
import lk.ijse.Entity.UserEntity;
import lk.ijse.dao.custom.UserDao;
import lk.ijse.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(UserEntity userEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.persist(userEntity);
        transaction.commit();

    }

    @Override
    public void update(UserEntity userEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.update(userEntity);
        transaction.commit();

    }

    @Override
    public void delete(UserEntity userEntity) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(userEntity);
        transaction.commit();


    }

    @Override
    public UserEntity search(String id) {

        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        UserEntity userEntity=session.get(UserEntity.class, id);
        transaction.commit();

        return userEntity;
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }
}
