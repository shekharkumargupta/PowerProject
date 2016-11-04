package com.opgea.crm.domain.model;

import org.apache.commons.fileupload.FileUpload;

public class FileUploadBean {

    private String documentName;
    private FileUpload file;
    private String documentType;
    private int documentTypeId;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public int getDocumentTypeId() {
        return documentTypeId;
    }
}
