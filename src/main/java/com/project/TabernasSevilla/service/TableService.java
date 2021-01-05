package com.project.TabernasSevilla.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.Table;
import com.project.TabernasSevilla.repository.TableRepository;

@Service
@Transactional
public class TableService {
	
	@Autowired
	private TableRepository tableRepo;
	
	//CRUD
	public Table create() {
		Table res = new Table();
		return res;
	}
	
	public void delete(Table table) {
		this.tableRepo.delete(table);
	}
	
	public Table findById(int id) {
		return this.tableRepo.findById(id).get();
	}
	
	public Table save(Table table) {
		return this.tableRepo.save(table);
	}
	
	//extra
	public Table quickCreate(Establishment est, int seating) {
		Table table = this.create();
		table.setEstablishment(est);
		table.setSeating(seating);
		Table saved = this.save(table);
		return saved;
	}
	
	public List<Table> findByEstablishment(Establishment establishment){
		return this.tableRepo.findByEstablishment(establishment.getId());
	}
	
	public List<Table> findAll(){
		return this.tableRepo.findAll();
	}
	
}
