package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	//method for deleted category
	List<Category> findByIsDeleted(int isDeleted);
}