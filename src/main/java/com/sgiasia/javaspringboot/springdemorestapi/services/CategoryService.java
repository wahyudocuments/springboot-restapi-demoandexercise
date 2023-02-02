package com.sgiasia.javaspringboot.springdemorestapi.services;

import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Category;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import com.sgiasia.javaspringboot.springdemorestapi.models.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save (Category category){
        return categoryRepo.save(category);
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepo.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    }

    public List<Category> findByName(String name){
        return categoryRepo.findByNameContains(name);
    }

}
