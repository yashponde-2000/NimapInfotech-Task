package com.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.product.dto.ProductJoin;
import com.product.entity.Product;
import com.product.exception.ResourceNotFoundException;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
    private static final int PAGE_SIZE = 10;
	
	@Autowired
    private ProductRepository proRepo;
	
	@Override
    public Page<Product> getAllProducts(int page) {
		
        return proRepo.findAll(PageRequest.of(page, PAGE_SIZE));
    }

//	@Override
//	public List<Product> getAllProducts() {
//	
//		  return proRepo.findByIsDeleted(1);
//	}

//	@Override
//	public Product getProductById(long id) {
//		
//		return proRepo.findById(id).orElse(null);
//	}

	@Override
	public Product createProduct(Product product) {
		
		 return proRepo.save(product);
	}
	
	
	

	@Override
	public Product updateProduct(int id, Product product) {

		Product existing = proRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setProductName(product.getProductName());    
            return proRepo.save(existing);
        }
        return null;
	}

	@Override
	public void deleteProduct(int id) {
		
		 Product existing = proRepo.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setIsDeleted(0);
	            proRepo.save(existing);
	        }
	}

	@Override
	public List<Product> getDeletedProducts() {
	
		return proRepo.findByIsDeleted(0); // Fetch only deleted ones (isDeleted = 0)
	}

	@Override
	public void restoreProduct(int id) {
		
		 Product product = proRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("product not found with id: " + id));
		 product.setIsDeleted(1); // Restore by setting isDeleted to 1
		 proRepo.save(product);
	}

	
	
    @Override
    public ProductJoin getProductJoinById(int id) {
        Product product = proRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return new ProductJoin(
                product.getProductId(),
                product.getProductName(),
                product.getCategory().getCategoryId(),
                product.getCategory().getCategoryName()
        );
    }

}
