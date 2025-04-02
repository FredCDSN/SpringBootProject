package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.repositories.ProductRepository;

@Service
public class ProductService {

	
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
}
