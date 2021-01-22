package org.example.domain.dao;

import org.example.domain.entity.Car;

import java.util.List;

public interface CarDao {
    void save(Car... car);

    Car find(int id);

    List<Car> findAll();

    void update(Car car);

    void delete(int id);
}
