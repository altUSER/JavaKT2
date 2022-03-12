package com.company.dao;

import com.company.model.CGood;
import com.company.model.COrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDAOOrders implements IDAO<COrder>{
    private SessionFactory sessionFactory;
    public CDAOOrders(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public COrder get(UUID id) {
        COrder order = null;
        try(Session session = sessionFactory.openSession()) {
            order = session.get(COrder.class, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<COrder> getAll(){
        List<COrder> orders;
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();
            orders = session.createQuery("from COrder").list();
            transaction.commit();
        }
        catch(Exception e) {
            orders = new ArrayList<>();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void save(COrder order) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void saveList(List<COrder> orders) {
        int objects_in_one_transaction = 1000;
        try(Session session = sessionFactory.openSession()) {
            for (int i = 0; i < orders.size(); i++) {
                session.beginTransaction();
                for (int j = 0; j < objects_in_one_transaction && i < orders.size(); j++, i++)
                    session.save(orders.get(i));
                session.getTransaction().commit();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateList(List<COrder> orders) {
        int objects_in_one_transaction = 1000;
        try(Session session = sessionFactory.openSession()) {
            for (int i = 0; i < orders.size(); i++) {
                session.beginTransaction();
                for (int j = 0; j < objects_in_one_transaction && i < orders.size(); j++, i++)
                    session.update(orders.get(i));
                session.getTransaction().commit();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(COrder order) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(COrder order) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}