package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void init() {

    }

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = true;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            item.setId(Integer.parseInt(id));
            if (session.get(Item.class, Integer.parseInt(id)) != null) {
                session.clear();
                session.update(item);
                session.flush();
            } else {
                result = false;
            }
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = true;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Item item = session.get(Item.class, Integer.parseInt(id));
            if (item != null) {
                session.clear();
                session.delete(item);
                session.flush();
            } else {
                result = false;
            }
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.Item").list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from ru.job4j.tracker.Item where name = :paramName");
            query.setParameter("paramName", key);
            result = query.list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Item.class, Integer.parseInt(id));
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
