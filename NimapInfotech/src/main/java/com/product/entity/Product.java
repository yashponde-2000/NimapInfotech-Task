package com.product.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	@Column(length=100, nullable = false)
	private String productName;
	

	//isDeleted is use to avoid the conflicts between the relationship between the two tables
    //using this we can perform the operation on deleted data like deleted data list,restore the deleted data
	@Column(nullable = false)
	private int isDeleted = 1;
	
	
	//build relationship between product and category as many product and one category
	 @ManyToOne
	 @JoinColumn(name = "category_id", nullable = false)
//	 @JsonIgnore // Prevent infinite recursion
	 @JsonBackReference // Marks this side as the "child" in the relationship
	 private Category category;

}
