package com.sgiasia.javaspringboot.springdemorestapi.services;

import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Supplier;
import com.sgiasia.javaspringboot.springdemorestapi.models.repos.CategoryRepo;
import com.sgiasia.javaspringboot.springdemorestapi.models.repos.SupplierRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if(!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeone(Long id){
        supplierRepo.deleteById(id);
    }

    public List<Supplier> findByName(String name){
        return supplierRepo.findByNameContains(name);
    }



}
