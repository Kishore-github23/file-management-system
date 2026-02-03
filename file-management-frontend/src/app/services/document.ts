// src/app/services/document.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Document, ApiResponse } from '../models/document.model';

@Injectable({
  providedIn: 'root',
})
export class DocumentService {
  private apiUrl = `${environment.apiUrl}/files`;

  constructor(private http: HttpClient) {}

  /**
   * Upload a file
   */
  uploadFile(file: File): Observable<Document> {
    const formData = new FormData();
    formData.append('file', file);
    
    return this.http.post<Document>(this.apiUrl, formData);
  }

  /**
   * Get all user documents
   */
  getUserDocuments(): Observable<Document[]> {
    return this.http.get<Document[]>(this.apiUrl);
  }

  /**
   * Download a file
   */
  downloadFile(id: number, fileName: string): void {
    this.http.get(`${this.apiUrl}/${id}`, { 
      responseType: 'blob' 
    }).subscribe(blob => {
      // Create a download link
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = fileName;
      link.click();
      
      // Clean up
      window.URL.revokeObjectURL(url);
    });
  }

  /**
   * Delete a file
   */
  deleteFile(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(`${this.apiUrl}/${id}`);
  }

  /**
   * Format file size for display
   */
  formatFileSize(bytes: number): string {
    if (bytes === 0) return '0 Bytes';
    
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    
    return Math.round(bytes / Math.pow(k, i) * 100) / 100 + ' ' + sizes[i];
  }
}