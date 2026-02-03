package com.filemanagement.controller;

import com.filemanagement.dto.ApiResponse;
import com.filemanagement.dto.FileResponse;
import com.filemanagement.model.FileEntity;
import com.filemanagement.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

private final FileService fileService;
    
 
//upload file
    @PostMapping
    public ResponseEntity<FileResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            Authentication auth) throws IOException {
        
        String username = auth.getName();
        FileResponse response = fileService.uploadFile(file, username);
        return ResponseEntity.ok(response);
    }
    

    //fetch all users
    @GetMapping
    public ResponseEntity<List<FileResponse>> getUserFiles(Authentication auth) {
        String username = auth.getName();
        List<FileResponse> files = fileService.getUserFiles(username);
        return ResponseEntity.ok(files);
    }
    

    //Download a file
    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable Long id,
            Authentication auth) {
        
        String username = auth.getName();
        
        // Get file metadata for headers
        FileEntity metadata = fileService.getFileMetadata(id, username);
        
        // Get file content
        Resource resource = fileService.downloadFile(id, username);
        
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(metadata.getFiletype()))
            .header(HttpHeaders.CONTENT_DISPOSITION, 
                    "attachment; filename=\"" + metadata.getFilename() + "\"")
            .body(resource);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFile(
            @PathVariable Long id,
            Authentication auth) {
        
        String username = auth.getName();
        fileService.deleteFile(id, username);
        
        return ResponseEntity.ok(new ApiResponse(true, "File deleted successfully"));
    }
}
