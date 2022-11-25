package com.tech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.model.Product;
import com.tech.service.KafkaSender;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	KafkaSender kafkaSender;
	
	@PostMapping
	public String createProduct(@RequestBody Product product) {		
		kafkaSender.sendMessage(product);
		return "Product Creation in is process..... ";
	}

	@PostMapping("/list")
	public String createProduct(@RequestBody List<Product> products) {
		
		products.forEach(product -> {
			kafkaSender.sendMessage(product);	
		});
		
		return "Product Creation in is process..... ";
	}

	@PostMapping("/list2")
	public String createProduct2(@RequestBody List<Product> products) {
		
		kafkaSender.sendMessage2(products);	
		
		return "Product Creation in is process..... ";
	}
}
