package com.filemanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name="documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String filename;
	
	@Column(nullable=false)
	private String filepath;
	
	@Column(nullable=false)
	private Long fileSize;
	
	@Column(nullable=false)
	private String contentType;
	
	@Column(name="uploaded_at")
	private LocalDateTime uploadedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@PrePersist
	protected void onCreate() {
		uploadedAt=LocalDateTime.now();
	}
	
	
}

