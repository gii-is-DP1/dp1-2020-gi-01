package com.project.TabernasSevilla.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    
    @NotBlank
    @Column(name = "comment", nullable = false, length = 1000)
    private String comment;
    
    @NotNull
    @Column(name = "rating", nullable = true)
    private Double rating;
    
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