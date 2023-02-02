package com.sgiasia.javaspringboot.springdemorestapi.controllers;

import com.sgiasia.javaspringboot.springdemorestapi.dto.CategoryData;
import com.sgiasia.javaspringboot.springdemorestapi.dto.ResponseData;
import com.sgiasia.javaspringboot.springdemorestapi.dto.SupplierData;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Category;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Supplier;
import com.sgiasia.javaspringboot.springdemorestapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        /** Tanpa Model Mapper */
//        Supplier supplier = new Supplier();
//        supplier.setName(supplierData.getName());
//        supplier.setAddress(supplierData.getAddress());
//        supplier.setEmail(supplierData.getEmail());

        /** Dengan Model Mapper */
        Category category = modelMapper.map(categoryData, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }


    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
        ResponseData<Category> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        /** Tanpa Model Mapper */
//        Supplier supplier = new Supplier();
//        supplier.setName(supplierData.getName());
//        supplier.setAddress(supplierData.getAddress());
//        supplier.setEmail(supplierData.getEmail());

        /** Dengan Model Mapper */
        Category category = modelMapper.map(categoryData, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/name/{name}")
    public List<Category> findByName(@PathVariable("name") String name){
        return categoryService.findByName(name);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        categoryService.removeOne(id);
    }



}
