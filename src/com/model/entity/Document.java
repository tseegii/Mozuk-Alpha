/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tseegii
 */
@Entity
@Table(name = "DOCUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findById", query = "SELECT d FROM Document d WHERE d.id = :id"),
    @NamedQuery(name = "Document.findByForeignKey", query = "SELECT d FROM Document d WHERE d.foreignKey = :foreignKey"),
    @NamedQuery(name = "Document.findByDescription", query = "SELECT d FROM Document d WHERE d.description = :description"),
    @NamedQuery(name = "Document.findByFileName", query = "SELECT d FROM Document d WHERE d.fileName = :fileName"),
    @NamedQuery(name = "Document.findByFileType", query = "SELECT d FROM Document d WHERE d.fileExtension = :fileExtension"),
    @NamedQuery(name = "Document.findByCreatedDate", query = "SELECT d FROM Document d WHERE d.createdDate = :createdDate"),
    @NamedQuery(name = "Document.findByDocumentType", query = "SELECT d FROM Document d WHERE d.documentTypeId = :documentTypeId"),
    @NamedQuery(name = "Document.findByEmployee", query = "SELECT d FROM Document d WHERE d.employeeCode = :employeeCode"),
})
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "foreign_key")
    private String foreignKey;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_extension")
    private String fileExtension;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "document_date")
    @Temporal(TemporalType.DATE)
    private Date documentDate;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "content_type")
    private String contentType;
    @JoinColumn(name = "document_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocumentType documentTypeId;
    @JoinColumn(name = "employee_code",referencedColumnName = "code")
    @ManyToOne
    private Employee employeeCode;
    @JoinColumn(name = "username",referencedColumnName = "username")
    @ManyToOne
    private Users users;

    public Document() {
    }

    public Document(BigDecimal id) {
        this.id = id;
    }

    public Document(BigDecimal id, String foreignKey, String fileName) {
        this.id = id;
        this.foreignKey = foreignKey;
        this.fileName = fileName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public DocumentType getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(DocumentType documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.entity.Document[ id=" + id + " ]";
    }


    @Transient
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
