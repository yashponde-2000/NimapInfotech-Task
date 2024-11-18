package com.product.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Category {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(length=100, nullable = false)
	private String categoryName;
	
	
	//isDeleted is use to avoid the conflicts between the relationship between the two tables
    //using this we can perform the operation on deleted data like deleted data list,restore the deleted data
	@Column(nullable = false)
	private int isDeleted = 1;
	
	//build relationship between product and category as many product and one category
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL ,orphanRemoval = true)
	@JsonManagedReference // Marks this side as the "parent" in the relationship
	private List<Product> products;
	
	
	
	
	

}
