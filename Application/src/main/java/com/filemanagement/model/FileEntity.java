package com.filemanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="files")
@Data
public class FileEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String filename;
    
    @Column(nullable = false)
    private String filepath; // Where file is stored on disk
    
    private Long filesize; // Size in bytes
    
    private String filetype; // MIME type
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private LocalDateTime uploadedAt;
    
    @PrePersist
    void onCreate() {
        uploadedAt = LocalDateTime.now();
    }
}
