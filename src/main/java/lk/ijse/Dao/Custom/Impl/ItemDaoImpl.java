package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Dao.Custom.ItemDao;
import lk.ijse.Entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return false;
    }

    @Override
    public boolean delete(Item object) {
        return false;
    }

    @Override
    public Item findById(String id) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return List.of();
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

