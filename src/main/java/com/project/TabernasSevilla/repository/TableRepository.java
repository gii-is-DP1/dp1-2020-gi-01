package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.Table;

public interface TableRepository extends JpaRepository<Table,Integer>{

	@Query("SELECT t FROM Table t WHERE t.establishment.id = ?1")
	public List<Table> findByEstablishment(int establishmentId);
}
