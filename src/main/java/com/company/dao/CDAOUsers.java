package com.company.dao;

import com.company.model.CUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CDAOUsers implements IDAO<CUser>{

    private SessionFactory sessionFactory;
    public CDAOUsers(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CUser get(UUID id){

        CUser user = new CUser();
        try(Session session = sessionFactory.openSession()){
            user = session.get(CUser.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<CUser> getAll(){
        List<CUser> users;
        try(Session session = sessionFactory.openSession()){
            users = session.createQuery("from CUser").list();
        } catch (Exception e){
            e.printStackTrace();
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public void save(CUser user){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveList(List<CUser> users){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for (CUser user:users) {
                session.save(user);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(CUser user){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateList(List<CUser> users)
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

    @Override
    public void delete(CUser user){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
