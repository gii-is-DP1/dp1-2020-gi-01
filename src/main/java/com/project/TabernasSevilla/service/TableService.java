package com.project.TabernasSevilla.service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
	public Long getOccupancyAtRestaurant(Establishment est) {
		return this.tableRepo.countOccupiedByEstablishment(est.getId());
	}
	
	public Long countFreeTables(Establishment est) {
		return this.tableRepo.countFreeTables(est.getId());
	}
	
	//hard coded, ugly way
	public String estimateFreeTable(Establishment est) {
		String res="";
		List<RestaurantTable> tables = this.findByEstablishment(est);
		RestaurantTable oldest = null;
		Duration oldestDuration = Duration.ofMinutes(0);
		for(RestaurantTable t: tables) {
			if(t.getHourSeated()!=null) {
				Duration currentDuration = Duration.between(t.getHourSeated(), Instant.now());
				int compare = oldestDuration.compareTo(currentDuration);
				if(compare < 0) {
					oldestDuration = currentDuration;
					oldest = t;
				}
			}else {
				oldest = null;
				break;
			}
		}
		if(oldest == null) {
			res = "Table available right now";
		}else {
			Instant estimate = oldest.getHourSeated().plus(1,ChronoUnit.HOURS);
			Duration dur = Duration.between(Instant.now(), estimate);
			//TODO: parse this shit string
			res = "Estimated wait of: "+dur.toString();
		}
		return res;
	}
	
	public Instant estimateFreeTableInstant(Establishment est) {
		Instant res = Instant.now();
		List<RestaurantTable> tables = this.findByEstablishment(est);
		for(RestaurantTable t: tables) {
			if(t.getHourSeated()!=null) {
				int compare = res.compareTo(t.getHourSeated());
				if(compare < 0) {
					res = t.getHourSeated();
				}
			}else {
				break;
			}
		}

		return res;
	}
	
	public RestaurantTable quickCreate(Establishment est, int seating) {
		RestaurantTable table = this.create();
		table.setEstablishment(est);
		table.setNumber(generateNumber(est));
		table.setSeating(seating);
		table.setOccupied(0);
		RestaurantTable saved = this.save(table);
		return saved;
	}
	
	public Integer generateNumber(Establishment est) {
		Integer num = this.tableRepo.findBiggestTableNumber(est.getId());
		if(num != null) {
			return num+1;
		}else {
			return 1;
		}
	}
	
	public List<RestaurantTable> findByEstablishment(Establishment establishment){
		return this.tableRepo.findByEstablishment(establishment.getId());
	}
	
	public List<RestaurantTable> findAll(){
		return this.tableRepo.findAll();
	}
	
	public List<RestaurantTable> findBooked(Establishment est){
		return this.tableRepo.findBookedByEstablishment(est.getId());
	}
	
}
