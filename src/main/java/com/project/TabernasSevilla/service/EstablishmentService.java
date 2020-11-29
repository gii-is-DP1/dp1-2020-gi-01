package com.project.TabernasSevilla.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.repository.EstablishmentRepository;

@Service
@Transactional
public class EstablishmentService {
	
	@Autowired
	private EstablishmentRepository establishmentRepo;
	
	public List<Establishment> findAll(){
		return this.establishmentRepo.findAll();
	}
	
	public Establishment findById(Integer id) {
		Optional<Establishment> est = this.establishmentRepo.findById(id);
		return est.isPresent() ? est.get() : null;
	}
}
