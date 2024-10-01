package lk.ijse.Config;

import lk.ijse.Entity.Customer;

import lk.ijse.Entity.Item;
import lk.ijse.Entity.Order;
import lk.ijse.Entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;
    private FactoryConfiguration() {
        Configuration cfg = new Configuration().configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class);
        sessionFactory = cfg.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
            factoryConfiguration = new FactoryConfiguration():factoryConfiguration;
        }
        public Session getSession() {
        return sessionFactory.openSession();
        }
    }

