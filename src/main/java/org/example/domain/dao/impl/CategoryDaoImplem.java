package org.example.domain.dao.impl;

import org.example.domain.dao.CategoryDao;
import org.example.domain.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryDaoImplem implements CategoryDao {


    private final SessionFactory sessionFactory;

    public CategoryDaoImplem() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void save(Category... categories) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Category b : categories) {
                Integer id = (Integer) session.save(b);
                System.out.println("Id: " + id);
            }
            tx.commit();
        } catch (Throwable ex) {
            System.out.println("Возникла ошибка при добавлении категории, возможно такая категория уже существует !!!");
            tx.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Category find(int id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Category> categories = null;
        try {
            categories = session.createQuery("from Category ", Category.class).list();
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return categories;
    }

    @Override
    public void update(Category category) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(category);
            tx.commit();
            System.out.println("Название категории успешно обновленно !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при обновлении категории !!!");
        } finally {
            session.close();
        }

    }


    @Override
    public void delete(int id) {

    }

}
