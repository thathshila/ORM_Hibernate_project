package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Dao.Custom.ItemDao;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Long code) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Item> query = session.createNativeQuery("delete from item where code = :code");
        query.setParameter("code", code);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Item findById(Long id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        Item item = null;

        try {
            transaction = session.beginTransaction();

            NativeQuery<Item> query = session.createNativeQuery("select * from item where code = :code", Item.class);
            query.setParameter("code", id);

            item = query.uniqueResult();
            transaction.commit(); // Committing the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an exception
            }
            e.printStackTrace(); // Logging the error
        } finally {
            session.close(); // Closing the session
        }

        return item;
    }

    @Override
    public List<Item> findAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("select * from item");
        query.addEntity(Item.class);
        List<Item> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Long> getItemCode() {
        Session session = null;
        Transaction transaction = null;
        List<Long> itemCode = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Use HQL to fetch only the customer IDs
            itemCode = session.createQuery("SELECT i.code FROM Item i", Long.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return itemCode;
    }
    }

