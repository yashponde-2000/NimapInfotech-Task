package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductJoin;
import com.product.entity.Product;
import com.product.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService provice;
	
    //To fetch the products page wise , default page is 0 i.e. It will show the entries 1-10
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
    	
        return provice.getAllProducts(page);
    }
	

     //get the product along with the category using id
	 @GetMapping("/{id}")
	  public ResponseEntity<ProductJoin> getProductJoinById(@PathVariable int id) {
	      ProductJoin product = provice.getProductJoinById(id);
	      return ResponseEntity.ok(product);
    }

	 //method to create the new product
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return provice.createProduct(product);
    }

    //method to update the product using id
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updated = provice.updateProduct(id, product);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    //method to delete the product using id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        provice.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    //method to get all the deleted products
    @GetMapping("/deleted")
    public ResponseEntity<List<Product>> getDeletedProducts() {
        List<Product> deletedProducts = provice.getDeletedProducts();
        return ResponseEntity.ok(deletedProducts);
    }
    
    //method to restore the deleted product to main list using id
    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreProduct(@PathVariable int id) {
        provice.restoreProduct(id);
        return ResponseEntity.ok().build();
    }
    
    

   
	
	

	
	
}
