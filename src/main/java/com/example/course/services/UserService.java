package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User inset(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
			/*}catch(RuntimeException e ){ isso é pra ver qual excessão que da no console.
			 * e.printStacktTrace;
			 * } */
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public User update(Long id, User obj) {
		try {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e){
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	
}
