package com.project.TabernasSevilla.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@Access(AccessType.PROPERTY)
@NoArgsConstructor 
public class Waiter extends Actor{

}
