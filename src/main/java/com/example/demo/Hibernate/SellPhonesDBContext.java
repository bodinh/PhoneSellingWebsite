package com.example.demo.Hibernate;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class SellPhonesDBContext {
    public static SessionFactory sessionFactory = new Configuration().configure("hibrateConfigXml/hibernate.cfg.xml").buildSessionFactory();

    public static Session getSession() {
        try {
            Session session = sessionFactory.openSession();
            return session;
        } catch (HibernateException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static void addNewObject(Object o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(o);
        transaction.commit();
        session.close();
    }

    public static void deleteObject(Object o){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(o);
        transaction.commit();
        session.close();
    }

    public static void updateObject(Object o){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(o);
        transaction.commit();
        session.close();
    }

}
