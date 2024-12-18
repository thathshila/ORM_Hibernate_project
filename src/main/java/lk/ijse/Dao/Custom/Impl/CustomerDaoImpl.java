package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Dao.Custom.CustomerDao;
import lk.ijse.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean  save(Customer object) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer object) {
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
        NativeQuery<Customer> nativeQuery = session.createNativeQuery("delete from customer where id = :id");
        nativeQuery.setParameter("id", code);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Customer findById(Long id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        Customer customer = null;

        try {
            transaction = session.beginTransaction();

            // Using native query to fetch the customer by ID
            NativeQuery<Customer> nativeQuery = session.createNativeQuery("SELECT * FROM customer WHERE id = :id", Customer.class);
            nativeQuery.setParameter("id", id);

            customer = nativeQuery.uniqueResult(); // Fetching single result

            transaction.commit(); // Committing the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an exception
            }
            e.printStackTrace(); // Logging the error
        } finally {
            session.close(); // Closing the session
        }

        return customer; // Returning the fetched customer
    }



    @Override
    public List<Customer> findAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Customer");
        query.addEntity(Customer.class);
        List<Customer> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public List<Long> getCustomerId() {
        Session session = null;
        Transaction transaction = null;
        List<Long> customerIds = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Use HQL to fetch only the customer IDs

            customerIds = session.createQuery("SELECT c.id FROM Customer c", Long.class).list();

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
        return customerIds;

    }

    @Override
    public Customer getCustomerById(Long id) {
        Session session = null;
        Transaction transaction = null;
        Customer customer = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<Customer> query = session.createNativeQuery("SELECT * FROM customer WHERE id = :id", Customer.class);
            query.setParameter("id", id);

            customer = query.uniqueResult(); // Execute query and set the result to customer

            transaction.commit(); // Commit the transaction if successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction if an error occurs
            }
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed
            }
        }

        return customer;
    }

}

