package com.project.TabernasSevilla.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Promotion extends BaseEntity {

	private String title;
	private String description;
	private String code;
	private Integer uses;
	private String startDate;
	private String endDate;
}
