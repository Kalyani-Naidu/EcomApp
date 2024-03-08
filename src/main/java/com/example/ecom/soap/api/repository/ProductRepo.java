package com.example.ecom.soap.api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecom.soap.api.entities.ProductData;

public interface ProductRepo extends MongoRepository<ProductData, String>{


}
