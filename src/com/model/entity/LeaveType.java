package com.model.entity;

import javax.persistence.*;

/**
 * Created by tseegii on 6/23/15.
 */
@Entity
@Table(name = "Leave_Type", schema = "", catalog = "avocado")
public class LeaveType {
    private int id;
    private String leaveType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "leave_type")
    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeaveType leaveType1 = (LeaveType) o;

        if (id != leaveType1.id) return false;
        if (leaveType != null ? !leaveType.equals(leaveType1.leaveType) : leaveType1.leaveType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (leaveType != null ? leaveType.hashCode() : 0);
        return result;
    }
}
