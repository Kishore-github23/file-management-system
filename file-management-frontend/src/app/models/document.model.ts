export interface Document {
  id: number;
  filename: string;
  filesize: number;
  filetype: string;
  uploadedAt: string;
}

/**
 * API response wrapper
 */
export interface ApiResponse {
  success: boolean;
  message: string;
  data?: any;
}