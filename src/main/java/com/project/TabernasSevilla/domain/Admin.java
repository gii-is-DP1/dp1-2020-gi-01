package com.project.TabernasSevilla.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Access(AccessType.PROPERTY)
@NoArgsConstructor 
public class Admin extends Actor{

}
