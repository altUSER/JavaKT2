package com.company.dao;

import com.company.model.CGood;
import com.company.model.COrder;
import com.company.model.COrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDAOOrders implements IDAO<COrder>{

    private SessionFactory sessionFactory;
    public CDAOOrders(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public COrder get(UUID id){

        COrder order = new COrder();
        try(Session session = sessionFactory.openSession()){
            order = session.get(COrder.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<COrder> getAll(){
        List<COrder> orders;
        try(Session session = sessionFactory.openSession()){
            orders = session.createQuery("from COrder").list();
        } catch (Exception e){
            e.printStackTrace();
            orders = new ArrayList<>();
        }
        return orders;
    }

    @Override
    public void save(COrder order){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveList(List<COrder> orders){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for (COrder order:orders) {
                session.save(order);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(COrder order){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(COrder order){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<COrder> getByGood(CGood good){
        List<COrder> orders;
        try(Session session = sessionFactory.openSession())
        {
            Query<COrder> query = session.createQuery("SELECT o from COrder o JOIN o.goods g where g.id=:id");
            query.setParameter("id", good.getId());

            orders =         query.list();
        }
        catch(Exception e)
        {
            orders = new ArrayList<>();
            e.printStackTrace();
        }
        return orders;

    }
}
