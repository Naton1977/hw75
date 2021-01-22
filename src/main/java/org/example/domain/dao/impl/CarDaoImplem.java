package org.example.domain.dao.impl;

import org.example.domain.dao.CarDao;
import org.example.domain.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDaoImplem implements CarDao {
    private final SessionFactory sessionFactory;

    public CarDaoImplem() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }


    @Override
    public void save(Car... cars) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Car cr : cars) {
                Integer id = (Integer) session.save(cr);
                System.out.println("Id: " + id);
            }
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Car find(int id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Car> cars = null;
        try {
            cars = session.createQuery("from Car ", Car.class).list();
            tx.commit();
        } catch (Throwable ex) {
            tx.rollback();
        } finally {
            session.close();
        }
        return cars;
    }

    @Override
    public void update(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(car);
            tx.commit();
            System.out.println("Данные автомобиля успешно обновленны !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возникла ошибка при обновлении данных автомобиля !!!");
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            Car car = session.get(Car.class, id);
            session.delete(car);
            tx.commit();
            System.out.println("Автомобиль успешно удален !!!");
        } catch (Throwable ex) {
            tx.rollback();
            System.out.println("Возгикла ошибка при удалении автомобиля !!!");
        } finally {
            session.close();
        }
    }
}
