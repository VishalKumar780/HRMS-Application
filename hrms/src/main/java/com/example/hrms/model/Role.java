package com.example.hrms.model;
import com.example.hrms.model.enums.RoleType;

import jakarta.persistence.*;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleType name;
    

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public RoleType getName() {
		return name;
	}
	
	public void setName(RoleType name) {
		this.name = name;
	}
}
