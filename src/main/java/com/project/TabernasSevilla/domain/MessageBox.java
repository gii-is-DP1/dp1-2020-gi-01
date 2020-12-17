package com.project.TabernasSevilla.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageBox extends BaseEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	public Actor owner;
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	public MessageBox parent;
	@NotBlank
	public String name;
	@NotBlank
	@Pattern(regexp = "^" + MessageBox.ROOT + "|" + MessageBox.NOTIF + "|" + MessageBox.ARCHIVE + "$")
	public String category;

	public static final String ROOT = "ROOT";
	public static final String ARCHIVE = "ARCHIVE";
	public static final String NOTIF = "NOTIF";

}
