package com.filemanagement.dto;

import lombok.Data;

@Data
public class FileResponse {
	private Long id;
    private String filename;
    private Long filesize;
    private String filetype;
    private String uploadedAt;
    
    public FileResponse(Long id, String filename, Long filesize, String filetype, String uploadedAt) {
        this.id = id;
        this.filename = filename;
        this.filesize = filesize;
        this.filetype = filetype;
        this.uploadedAt = uploadedAt;
    }
}
