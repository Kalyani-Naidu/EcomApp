
package com.example.ecom.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.ecom.soap.api.ecomproducts.AddProductRequest;
import com.example.ecom.soap.api.ecomproducts.AddProductResponse;
import com.example.ecom.soap.api.ecomproducts.DeleteProductRequest;
import com.example.ecom.soap.api.ecomproducts.DeleteProductResponse;
import com.example.ecom.soap.api.ecomproducts.GetProductRequest;
import com.example.ecom.soap.api.ecomproducts.GetProductResponse;
import com.example.ecom.soap.api.ecomproducts.UpdateProductRequest;
import com.example.ecom.soap.api.ecomproducts.UpdateProductResponse;
import com.example.ecom.soap.api.service.Service;

@org.springframework.ws.server.endpoint.annotation.Endpoint
public class Endpoint {
	
	@Autowired
	private Service service;
	
	private static final String NAMESPACE = "http://www.example.com/ecom/soap/api/EcomProducts";
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "addProductRequest")
	@ResponsePayload
	public AddProductResponse addProduct(@RequestPayload AddProductRequest request) {
		AddProductResponse response = service.addProduct(request);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "getProductRequest")
	@ResponsePayload
	public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
		GetProductResponse response = service.getProduct(request);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "deleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest request) {
		DeleteProductResponse response = service.deleteProduct(request);
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "updateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) {
		UpdateProductResponse response = service.updateProduct(request);
		return response;
	}

}