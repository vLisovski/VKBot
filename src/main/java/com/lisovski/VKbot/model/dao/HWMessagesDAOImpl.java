package com.lisovski.VKbot.model.dao;

import com.lisovski.VKbot.model.connection.HibernateSession;
import com.lisovski.VKbot.model.entities.HWMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HWMessagesDAOImpl implements HWMessagesDAO {
    private final SessionFactory sessionFactory;
    public HWMessagesDAOImpl() {
        sessionFactory = HibernateSession.getInstance().getSessionFactory();
    }
    public List<HWMessage> getByPeerId(int peerId) {
        Session session = sessionFactory.openSession();

        String hql = "from HWMessage where peer_id = :peerId";
        Query<HWMessage> query = session.createQuery(hql, HWMessage.class);
        query.setParameter("peerId",peerId);
        List<HWMessage> list = query.list();

        session.close();

        return list;
    }

    public void addNew(HWMessage hwMessage){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(hwMessage);

        transaction.commit();
        session.close();
    }

    public void updateById(HWMessage hwMessage){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(hwMessage);

        transaction.commit();
        session.close();
    }
}
