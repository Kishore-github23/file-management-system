import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth';
import { DocumentService } from '../../services/document';
import { Document } from '../../models/document.model';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent implements OnInit {
  currentUser: User | null = null;
  documents: Document[] = [];
  selectedFile: File | null = null;
  isUploading = false;
  message = '';
  messageType: 'success' | 'error' = 'success';

  constructor(
    private authService: AuthService,
    private documentService: DocumentService
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.loadDocuments();
  }


  loadDocuments(): void {
    this.documentService.getUserDocuments().subscribe({
      next: (docs) => {
        this.documents = docs;
        console.log('Documents loaded:', docs); // Debug log
      },
      error: (error) => {
        console.error('Error loading documents:', error); // Debug log
        this.showMessage('Failed to load documents', 'error');
      }
    });
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadFile(): void {
    if (!this.selectedFile) {
      this.showMessage('Please select a file', 'error');
      return;
    }

    this.isUploading = true;
    this.documentService.uploadFile(this.selectedFile).subscribe({
      next: (response) => {
        this.showMessage('File uploaded successfully', 'success');
        this.loadDocuments();
        this.selectedFile = null;
        // Reset file input
        const fileInput = document.getElementById('fileInput') as HTMLInputElement;
        if (fileInput) fileInput.value = '';
      },
      error: (error) => {
        console.error('Upload error:', error); // Debug log
        this.showMessage(error.error?.message || 'Upload failed', 'error');
        this.isUploading = false;
      },
      complete: () => {
        this.isUploading = false;
      }
    });
  }


  downloadFile(doc: Document): void {
    this.documentService.downloadFile(doc.id, doc.filename);
  }



  deleteFile(id: number): void {
    if (confirm('Are you sure you want to delete this file?')) {
      this.documentService.deleteFile(id).subscribe({
        next: (response) => {
          this.showMessage('File deleted successfully', 'success');
          this.loadDocuments();
        },
        error: (error) => {
          console.error('Delete error:', error); // Debug log
          this.showMessage('Failed to delete file', 'error');
        }
      });
    }
  }


  formatSize(bytes: number): string {
    return this.documentService.formatFileSize(bytes);
  }


  formatDate(dateString: string): string {
    return new Date(dateString).toLocaleString();
  }


  logout(): void {
    this.authService.logout();
  }

  
  private showMessage(msg: string, type: 'success' | 'error'): void {
    this.message = msg;
    this.messageType = type;
    setTimeout(() => {
      this.message = '';
    }, 3000);
  }
}