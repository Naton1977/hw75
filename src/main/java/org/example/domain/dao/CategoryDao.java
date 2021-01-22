package org.example.domain.dao;

import org.example.domain.entity.Category;

import java.util.List;

public interface CategoryDao {
    void save(Category... category);

    Category find(int id);

    List<Category> findAll();

    void update(Category category);

    void delete(int id);
}
