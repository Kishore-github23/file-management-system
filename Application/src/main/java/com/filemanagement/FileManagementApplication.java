package com.filemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagementApplication.class, args);
		System.out.println();
        System.out.println("           ----> PFM is running on http://localhost:8081 <----        ");
        System.out.println();

	}

}
