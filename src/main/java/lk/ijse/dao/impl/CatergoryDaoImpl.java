package lk.ijse.dao.impl;

import jakarta.persistence.Id;

import lk.ijse.Entity.CatergoryEntity;
import lk.ijse.dao.custom.CatergoryDao;
import lk.ijse.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query; //1

import java.util.List; //2

public class CatergoryDaoImpl implements CatergoryDao {
    @Override
    public void save(CatergoryEntity catergoryEntity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(catergoryEntity);
            transaction.commit();
        }


    }

    @Override
    public void update(CatergoryEntity catergoryEntity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(catergoryEntity);
            transaction.commit();
        }

    }

    @Override
    public void delete(CatergoryEntity catergoryEntity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(catergoryEntity);
            transaction.commit();
        };


    }

    @Override
    public CatergoryEntity search(String id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CatergoryEntity categoryEntity = session.get(CatergoryEntity.class, id);
            return categoryEntity;
        }
    }

    @Override
    public List<CatergoryEntity> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CatergoryEntity ");
            List<CatergoryEntity> list = query.list();
            return list;
        }
    }


}
