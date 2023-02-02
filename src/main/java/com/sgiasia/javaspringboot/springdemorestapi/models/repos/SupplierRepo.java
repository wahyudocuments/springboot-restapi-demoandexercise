package com.sgiasia.javaspringboot.springdemorestapi.models.repos;

import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier,Long> {

    List<Supplier> findByNameContains(String name);
}
