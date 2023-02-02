package com.sgiasia.javaspringboot.springdemorestapi.controllers;

import com.sgiasia.javaspringboot.springdemorestapi.dto.ResponseData;
import com.sgiasia.javaspringboot.springdemorestapi.dto.SupplierData;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Product;
import com.sgiasia.javaspringboot.springdemorestapi.models.entities.Supplier;
import com.sgiasia.javaspringboot.springdemorestapi.models.repos.SupplierRepo;
import com.sgiasia.javaspringboot.springdemorestapi.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SupplierRepo supplierRepo;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
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

        logger.info(supplierData.toString());

        /** Dengan Model Mapper */
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

//    @GetMapping
//    public Iterable<Supplier> findAll(){
//        return supplierService.findAll();
//    }
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Supplier>>> findAll(){
        ResponseData<Iterable<Supplier>> responseData = new ResponseData<Iterable<Supplier>>();
        Iterable<Supplier> suppliers = supplierService.findAll();
        responseData.setStatus(true);
        responseData.setPayload(suppliers);
        responseData.getMessages().add("OK");
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Supplier>> findOne(@PathVariable("id") Long id){
        ResponseData<Supplier> responseData = new ResponseData<Supplier>();
        Supplier supplier = supplierService.findOne(id);
        responseData.setStatus(true);
        responseData.setPayload(supplier);
        responseData.getMessages().add("OK");
        return ResponseEntity.ok().body(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
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
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        supplierService.removeone(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseData<List<Supplier>>> findByName(@PathVariable("name") String name){
        ResponseData<List<Supplier>> responseData = new ResponseData<List<Supplier>>();
        List<Supplier> supplier = supplierService.findByName(name);
        responseData.setStatus(true);
        responseData.setPayload(supplier);
        responseData.getMessages().add("OK");
        return ResponseEntity.ok().body(responseData);
    }



}
