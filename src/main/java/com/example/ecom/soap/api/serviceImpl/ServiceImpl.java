
package com.example.ecom.soap.api.serviceImpl;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecom.soap.api.ecomproducts.AddProductRequest;
import com.example.ecom.soap.api.ecomproducts.AddProductResponse;
import com.example.ecom.soap.api.ecomproducts.DeleteProductRequest;
import com.example.ecom.soap.api.ecomproducts.DeleteProductResponse;
import com.example.ecom.soap.api.ecomproducts.GetProductRequest;
import com.example.ecom.soap.api.ecomproducts.GetProductResponse;
import com.example.ecom.soap.api.ecomproducts.Product;
import com.example.ecom.soap.api.ecomproducts.Status;
import com.example.ecom.soap.api.ecomproducts.UpdateProductRequest;
import com.example.ecom.soap.api.ecomproducts.UpdateProductResponse;
import com.example.ecom.soap.api.entities.ProductData;
import com.example.ecom.soap.api.repository.ProductRepo;
import com.example.ecom.soap.api.service.Service;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
	
	@Autowired
	private ProductRepo repo;

	@Override
	public AddProductResponse addProduct(AddProductRequest request) {
		AddProductResponse response = new AddProductResponse();
		ProductData product = new ProductData();
		Status status = new Status();
		try {
			BeanUtils.copyProperties(request.getProduct(), product);
			repo.save(product);
			status.setStatusCode("R");
			status.setStatusDesc("Product added to DB succesfully");
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatusCode("F");
			status.setStatusDesc("AddProductRequest failed with error: "+e.toString());
		}
		response.setStatus(status);
		return response;
	}

	@Override
	public GetProductResponse getProduct(GetProductRequest request) {
	    GetProductResponse response = new GetProductResponse();
	    Status status = new Status();
	    Product item = new Product();
	    System.out.println("ID: "+request.getId());
	    String _id = request.getId();
	        System.out.println("Copy properties: "+_id);
	        try {
	            if (repo.existsById(_id)) {
	            	 System.out.println("entered if loop");
	                Optional<ProductData> optional = repo.findById(_id);
	                System.out.println("Optional: "+optional);
	                ProductData product = optional.get();
	                System.out.println("product: "+product);
	                if (product != null && Integer.parseInt(product.getQuantity()) == 0) {
	                	System.out.println("quantity is 0");
	                    status.setStatusCode("F");
	                    status.setStatusDesc("Product is out of stock!");
	                    BeanUtils.copyProperties(product, item);
	                    response.setProduct(item);
	                    System.out.println("response: "+response.getProduct());
	                } else if (product != null && Integer.parseInt(product.getQuantity()) < request.getSelectedItems()) {
	                	System.out.println("quantity is less");
	                    status.setStatusCode("F");
	                    status.setStatusDesc("Required quantity is not present");
	                    System.out.println("status is set");
	                    BeanUtils.copyProperties(product, item);
	                    response.setProduct(item);
	                    System.out.println("response: "+response.getProduct());
	                } else {
	                	System.out.println("entered else...");
	                    status.setStatusCode("R");
	                    status.setStatusDesc("Proceed to buy");
	                    BeanUtils.copyProperties(product, item);
	                    response.setProduct(item);
	                    System.out.println("response: "+response.getProduct());
	                }
	            }
	        } catch (Exception e) {
	        	System.out.println("entered catch block");
	            e.printStackTrace();
	            status.setStatusCode("F");
	            status.setStatusDesc("GetProductRequest failed with error: " + e.toString());
	        }
	    response.setStatus(status);
	    return response;
	}



	@Override
	public UpdateProductResponse updateProduct(UpdateProductRequest request) {
		UpdateProductResponse response = new UpdateProductResponse();
		Status status = new Status();
		String _id = request.getId();
		int selectedItems = request.getSelectedItems();
		try {
			if(repo.existsById(_id)) {
				Optional<ProductData> optional = repo.findById(_id);
				ProductData product = optional.get();
				String existingQuantity = product.getQuantity();
				int newQuantity = Integer.parseInt(existingQuantity)-selectedItems;
				String quantity = String.valueOf(newQuantity);
				product.setQuantity(quantity);
				repo.save(product);
				status.setStatusCode("R");
				status.setStatusDesc("Product updated succesfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatusCode("F");
			status.setStatusDesc("UpdateProductRequest failed with error: "+e.toString());
		}
		response.setStatus(status);
		return response;
	}

	@Override
	public DeleteProductResponse deleteProduct(DeleteProductRequest request) {
		DeleteProductResponse response = new DeleteProductResponse();
		Status status = new Status();
		try {
			if(repo.existsById(request.getId())) {
				repo.deleteById(request.getId());
				status.setStatusCode("R");
				status.setStatusDesc("Product deleted succesfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatusCode("F");
			status.setStatusDesc("DeleteProductRequest failed with error: "+e.toString());
		}
		response.setStatus(status);
		return response;
	}

}
  