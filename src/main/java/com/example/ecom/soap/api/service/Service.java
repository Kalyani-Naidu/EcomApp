package com.example.ecom.soap.api.service;

import com.example.ecom.soap.api.ecomproducts.AddProductRequest;
import com.example.ecom.soap.api.ecomproducts.AddProductResponse;
import com.example.ecom.soap.api.ecomproducts.DeleteProductRequest;
import com.example.ecom.soap.api.ecomproducts.DeleteProductResponse;
import com.example.ecom.soap.api.ecomproducts.GetProductRequest;
import com.example.ecom.soap.api.ecomproducts.GetProductResponse;
import com.example.ecom.soap.api.ecomproducts.UpdateProductRequest;
import com.example.ecom.soap.api.ecomproducts.UpdateProductResponse;

public interface Service {
	
	public AddProductResponse addProduct(AddProductRequest request);
	
	public GetProductResponse getProduct(GetProductRequest request);
	
	public UpdateProductResponse updateProduct(UpdateProductRequest request);
	
	public DeleteProductResponse deleteProduct(DeleteProductRequest request);

}
