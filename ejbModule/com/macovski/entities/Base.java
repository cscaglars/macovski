package com.macovski.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Base  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5549400747135573273L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private Long Id;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
}
