/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author tseegii
 */
@Entity
@Table(name = "DEPARTMENT")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
        @NamedQuery(name = "Department.findByCode", query = "SELECT d FROM Department d WHERE d.code = :code"),
        @NamedQuery(name = "Department.findByDepartmentTitle", query = "SELECT d FROM Department d WHERE d.departmentTitle = :departmentTitle"),
        @NamedQuery(name = "Department.findByCreatedDate", query = "SELECT d FROM Department d WHERE d.createdDate = :createdDate")
})
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "department_title")
    private String departmentTitle;
    @Lob
    @Column(name = "department_description")
    private String departmentDescription;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Transient
    private Employee employeeCode;
    @OneToMany(mappedBy = "departmentCode")
    private List<EmployeePosition> employeePositionList;
    @OneToMany(mappedBy = "departmentCode")
    private List<Resolution> resolutionList;
    @OneToMany(mappedBy = "departmentCode")
    private List<Probation> probationList;

    @Basic
    @Column(name = "isActive")
    private Boolean isActive;

    public Department() {
    }

    public Department(String code) {
        this.code = code;
    }

    public Department(String code, String departmentTitle) {
        this.code = code;
        this.departmentTitle = departmentTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    @XmlTransient
    public List<EmployeePosition> getEmployeePositionList() {
        return employeePositionList;
    }

    public void setEmployeePositionList(List<EmployeePosition> employeePositionList) {
        this.employeePositionList = employeePositionList;
    }

    @XmlTransient
    public List<Resolution> getResolutionList() {
        return resolutionList;
    }

    public void setResolutionList(List<Resolution> resolutionList) {
        this.resolutionList = resolutionList;
    }

    @XmlTransient
    public List<Probation> getProbationList() {
        return probationList;
    }

    public void setProbationList(List<Probation> probationList) {
        this.probationList = probationList;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }





    @Override
    public String toString() {
        return "com.model.entity.Department[ code=" + code + " ]";
    }

}
