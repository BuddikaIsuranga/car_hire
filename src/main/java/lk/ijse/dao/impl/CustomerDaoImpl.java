package lk.ijse.dao.impl;

import lk.ijse.Entity.CustomerEntity;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void save(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.persist(customerEntity);
        transaction.commit();

    }

    @Override
    public void update(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.update(customerEntity);
        transaction.commit();

    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.delete(customerEntity);
        transaction.commit();

    }

    @Override
    public CustomerEntity search(String id) {

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.get(CustomerEntity.class, id);
            }
    }

    @Override
    public List<CustomerEntity> getAll() {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM CustomerEntity").list();
        }
    }


}
