package com.project.TabernasSevilla.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;
import com.project.TabernasSevilla.repository.TableRepository;

@Service
@Transactional
public class TableService {
	
	@Autowired
	private TableRepository tableRepo;
	
	//CRUD
	public RestaurantTable create() {
		RestaurantTable res = new RestaurantTable();
		return res;
	}
	
	public void delete(RestaurantTable table) {
		this.tableRepo.delete(table);
	}
	
	public RestaurantTable findById(int id) {
		return this.tableRepo.findById(id).get();
	}
	
	public RestaurantTable save(RestaurantTable table) {
		return this.tableRepo.save(table);
	}
	
	//extra
	public RestaurantTable quickCreate(Establishment est, int seating) {
		RestaurantTable table = this.create();
		table.setEstablishment(est);
		table.setSeating(seating);
		RestaurantTable saved = this.save(table);
		return saved;
	}
	
	public List<RestaurantTable> findByEstablishment(Establishment establishment){
		return this.tableRepo.findByEstablishment(establishment.getId());
	}
	
	public List<RestaurantTable> findAll(){
		return this.tableRepo.findAll();
	}
	
}
