package com.project.TabernasSevilla.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "review")
public class Review extends BaseEntity{
 
    @ManyToOne(fetch = FetchType.LAZY,optional=false)
	private Actor actor;
    
    @ManyToOne(fetch = FetchType.LAZY,optional=false)
	private Dish dish;
    
 
    @Column(name = "comment", nullable = false, length = 1000)
    private String comment;
 
    @Column(name = "rating", nullable = true)
    private int rating;
    
    /*
    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;
 
    @Column(name = "created", nullable = true)
    private Date created;    
 
    @PrePersist
      protected void onCreate() {
        created = new Date();
      }
 	*/
    //getters and setters
//toString method
 
}