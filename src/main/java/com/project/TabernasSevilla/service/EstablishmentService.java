package com.project.TabernasSevilla.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Cook;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.Manager;
import com.project.TabernasSevilla.domain.Waiter;
import com.project.TabernasSevilla.repository.EstablishmentRepository;

@Service
@Transactional
public class EstablishmentService {

 
	private EstablishmentRepository establishmentRepo;
 
	private CookService cookService;
 
	private WaiterService waiterService;
 
	private ManagerService managerService;

	
	@Autowired
	public EstablishmentService(EstablishmentRepository establishmentRepo, CookService cookService,
			WaiterService waiterService, ManagerService managerService) {
		super();
		this.establishmentRepo = establishmentRepo;
		this.cookService = cookService;
		this.waiterService = waiterService;
		this.managerService = managerService;
	}

	// CRUD
	public Establishment findById(Integer id) {
		Optional<Establishment> est = this.establishmentRepo.findById(id);
		return est.isPresent() ? est.get() : null;
	}
	
	public List<Establishment> findAll() {
		return this.establishmentRepo.findAll();
	}
	
	public Establishment save(Establishment est) {
		return this.establishmentRepo.save(est);
	}
	
	public Establishment create() {
		Establishment res = new Establishment();
		res.setCook(new ArrayList<Cook>());
		res.setManager(new ArrayList<Manager>());
		res.setWaiter(new ArrayList<Waiter>());
		res.setScore(0);
		res.setCapacity(1);

		return res;
	}
	
	public void delete(Establishment est) {
		this.establishmentRepo.delete(est);
	}

	//OTHER METHODS
	
	// find cooks by id and add them to restaurant
	public Establishment addAllCooks(Integer estId, Integer[] cookId) {
		Establishment est = this.findById(estId);
		List<Cook> cooks = new ArrayList<>();
		for (int i = 0; i < cookId.length; i++) {
			Cook c = this.cookService.findById(cookId[i]);
			cooks.add(c);
		}
		est.setCook(cooks);
		return this.save(est);
	}

	// find waiter by id and add them to restaurant
	public Establishment addAllWaiters(Integer estId, Integer[] waiterId) {
		Establishment est = this.findById(estId);
		List<Waiter> waiters = new ArrayList<>();
		for (int i = 0; i < waiterId.length; i++) {
			Waiter w = this.waiterService.findById(waiterId[i]);
			waiters.add(w);
		}
		est.setWaiter(waiters);
		return this.save(est);
	}
	
	// find manager by id and add them to restaurant
	public Establishment addAllManagers(Integer estId, Integer[] managerId) {
		Establishment est = this.findById(estId);
		List<Manager> managers = new ArrayList<>();
		for (int i = 0; i < managerId.length; i++) {
			Manager m = this.managerService.findById(managerId[i]);
			managers.add(m);
		}
		est.setManager(managers);
		return this.save(est);
	}
	
	

}
