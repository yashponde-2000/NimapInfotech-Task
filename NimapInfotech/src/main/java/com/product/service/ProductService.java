package com.product.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.product.dto.ProductJoin;
import com.product.entity.Product;


public interface ProductService {
	
    Page<Product> getAllProducts(int page);
	 //List<Product> getAllProducts();
	 
//	 Product getProductById(long id);
	 
	 ProductJoin getProductJoinById(int id);
	 
    Product createProduct(Product product);
   
	 Product updateProduct(int id, Product product);
	 
	 void deleteProduct(int id);
	 
	 List<Product> getDeletedProducts();
	 
	 void restoreProduct(int id);


}
