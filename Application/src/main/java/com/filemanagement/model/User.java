package com.filemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String username;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;// Will be stored as Bcrypt hash
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Document>documents=new ArrayList<>();
	
	@PrePersist
	protected void onCreate() {
		createdAt=LocalDateTime.now();
	}
	
	

}
