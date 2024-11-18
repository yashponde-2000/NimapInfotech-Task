package com.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//for fetching the data from both product and category tables
public class ProductJoin {
	
	   private int productId;
	   
	   private String productName;
	   
	   private int categoryId;
	   
	   private String categoryName;

}
