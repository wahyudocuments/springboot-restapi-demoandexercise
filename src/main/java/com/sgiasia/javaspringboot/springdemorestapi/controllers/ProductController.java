package com.sgiasia.javaspringboot.springdemorestapi.controllers;

import com.sgiasia.javaspringboot.springdemorestapi.dto.ResponseData;
import com.sgiasia.javaspringboot.springdemorestapi.dto.SupplierData;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Supplier;
import com.sgiasia.javaspringboot.springdemorestapi.services.ProductService;
import com.sgiasia.javaspringboot.springdemorestapi.services.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors ){

        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        logger.info(responseData.toString());
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @GetMapping("/name/{name}")
    public List<Product> findByName(@PathVariable("name") String name){
        return productService.findByName(name);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    /** Tambahan Pak Dony */
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id,product);
    }

    @PutMapping("/{id}/setprice/{price}")
    public void updatePrice(@PathVariable("id") Long id, @PathVariable("price") double price , Product product){
        productService.updatePrice(id,price,product);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier,productId);
    }
}
