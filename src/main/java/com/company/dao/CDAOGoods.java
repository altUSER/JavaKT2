package com.company.dao;

import com.company.model.CGood;
import com.company.model.COrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDAOGoods implements IDAO<CGood>{
    private SessionFactory sessionFactory;
    public CDAOGoods(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CGood get(UUID id){

        CGood user = new CGood();
        try(Session session = sessionFactory.openSession()){
            user = session.get(CGood.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<CGood> getAll(){
        List<CGood> users;
        try(Session session = sessionFactory.openSession()){
            users = session.createQuery("from CGood").list();
        } catch (Exception e){
            e.printStackTrace();
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public void save(CGood user){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveList(List<CGood> users){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for (CGood user:users) {
                session.save(user);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(CGood user){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateList(List<CGood> users)
    {
        try(Session session = sessionFactory.openSession())
        {
            for (int i=0; i<users.size(); i++) {
                session.beginTransaction();
                for (int j = 0; j<1000 && i<users.size(); j++, i++)
                    session.update(users.get(i));
                session.getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<COrder> getGoodOrders(UUID gid)
    {
        CGood good = null;
        List<COrder> orders;
        try(Session session = sessionFactory.openSession())
        {
            good = session.get(CGood.class, gid);
            orders = good.getOrders();
        }
        catch(Exception e)
        {
            orders = new ArrayList<>();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void delete(CGood user){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
