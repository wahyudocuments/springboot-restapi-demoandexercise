package com.sgiasia.javaspringboot.springdemorestapi.models.repos;

import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Category;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    List<Category> findByNameContains(String name);
}
