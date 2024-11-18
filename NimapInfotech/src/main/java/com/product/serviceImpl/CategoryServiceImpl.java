package com.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.Category;
import com.product.exception.ResourceNotFoundException;
import com.product.repository.CategoryRepository;
import com.product.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
    private CategoryRepository catRepo;
	
	
    @Override
    public Page<Category> getAllCategories(int page) {
    	
	      return catRepo.findAll(PageRequest.of(page, PAGE_SIZE));
	}
    
//	@Override
//	public List<Category> getAllCategories() {
//	
//		  return catRepo.findByIsDeleted(1);
//	}

	@Override
	public Category getCategoryById(int id) {
		
		return catRepo.findById(id).orElse(null);
	}

	@Override
	public Category createCategory(Category category) {
		
		 return catRepo.save(category);
	}

	@Override
	public Category updateCategory(int id, Category category) {

		Category existing = catRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setCategoryName(category.getCategoryName());    
            return catRepo.save(existing);
        }
        return null;
	}

	@Override
	public void deleteCategory(int id) {
		
		 Category existing = catRepo.findById(id).orElse(null);
	        if (existing != null) {
	            existing.setIsDeleted(0);
	            catRepo.save(existing);
	        }
	}

	@Override
	public List<Category> getDeletedCategories() {
	
		return catRepo.findByIsDeleted(0); // Fetch only deleted ones (isDeleted = 0)
	}

	@Override
	public void restoreCategory(int id) {
		
		 Category category = catRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		 category.setIsDeleted(1); // Restore by setting isDeleted to 1
		 catRepo.save(category);
	}


}
