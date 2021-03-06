package com.model.bean;

import com.model.entity.*;
import com.model.other.CalendarPojo;
import com.model.util.BaseEJB;
import com.model.util.DataTypeUtils;
import com.model.util.SequenceUtil;
import org.joda.time.DateTime;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tseegii on 6/24/15.
 */
@LocalBean
@Stateless
public class OtherBean extends BaseEJB {


    @Inject
    private LeaveAbsenceBean leaveAbsenceBean;

    @Inject
    private OvertimeBean overtimeBean;

    /**
     * Ene bean jijeg heregtseet zvilvvdiig bichne
     */
    public Department findByDepartmentCode(String departmentCode) {
        return getEm().find(Department.class, departmentCode);
    }

    public List<Department> findAllDepartment() {
        List<Department> departments = getEm().createNamedQuery("Department.findAll", Department.class).getResultList();
        for (Department department : departments) {
            DepartmentHeads heads = null;
            try {
                heads = getEm().createNamedQuery("DepartmentHeads.findByDepartmentCodeAndIsActive", DepartmentHeads.class)
                        .setParameter("departmentCode", department.getCode())
                        .setParameter("isActive", true)
                        .getSingleResult();
                department.setEmployeeCode(getEm().find(Employee.class, heads.getEmployeeCode()));
            } catch (Exception e) {
                department.setEmployeeCode(null);
            }
        }
        return departments;
    }

    public List<Department> findDepartment() {
        List<Department> departments = getEm().createNamedQuery("Department.findAll", Department.class).getResultList();
        for (Department department : departments) {
            DepartmentHeads heads = null;
            try {
                heads = getEm().createNamedQuery("DepartmentHeads.findByDepartmentCodeAndIsActive", DepartmentHeads.class)
                        .setParameter("departmentCode", department.getCode())
                        .setParameter("isActive", true)
                        .getSingleResult();
                department.setEmployeeCode(getEm().find(Employee.class, heads.getEmployeeCode()));
            } catch (Exception e) {
                department.setEmployeeCode(null);
            }
        }
        return departments;
    }

    public Department saveByDepartment(Department department) {
        try {
//            department.setCode(SequenceUtil.nextBigDecimal().toString());
            department.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(department);
            if (department.getEmployeeCode() != null) {
                DepartmentHeads heads = new DepartmentHeads();
                heads.setId(SequenceUtil.nextBigDecimal());
                heads.setDepartmentCode(heads.getDepartmentCode());
                heads.setEmployeeCode(department.getEmployeeCode().getCode());
                heads.setIsActive(true);
                getEm().persist(heads);
            }
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Department updateByDepartment(Department department) {
        try {
            department = getEm().merge(department);
            if (department.getEmployeeCode() != null) {
                try {
                    DepartmentHeads head = getEm().createNamedQuery("DepartmentHeads.findByDepartmentCodeAndEmployeeCodeAndIsActive", DepartmentHeads.class)
                            .setParameter("departmentCode", department.getCode())
                            .setParameter("employeeCode", department.getEmployeeCode().getCode())
                            .setParameter("isActive", true)
                            .getSingleResult();
                } catch (Exception e) {
                    DepartmentHeads heads = getEm().createNamedQuery("DepartmentHeads.findByDepartmentCodeAndIsActive", DepartmentHeads.class)
                            .setParameter("departmentCode", department.getCode())
                            .setParameter("isActive", true)
                            .getSingleResult();
                    heads.setIsActive(false);
                    getEm().merge(heads);
                    DepartmentHeads newHead = new DepartmentHeads();
                    newHead.setId(SequenceUtil.nextBigDecimal());
                    newHead.setDepartmentCode(heads.getDepartmentCode());
                    newHead.setEmployeeCode(department.getEmployeeCode().getCode());
                    newHead.setIsActive(true);
                    getEm().persist(newHead);
                }
            }
            return department;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByDepartment(String departmentCode) {
        try {
            getEm().remove(getEm().getReference(Department.class, departmentCode));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public DocumentType findByDocumentTypeId(BigDecimal documentTypeId) {
        return getEm().find(DocumentType.class, documentTypeId);
    }

    public List<DocumentType> findAllDocumentType() {
        return getEm().createNamedQuery("DocumentType.findAll", DocumentType.class).getResultList();
    }

    public DocumentType saveByDocumentType(DocumentType documentType) {
        try {
            documentType.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(documentType);
            return documentType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DocumentType updateByDocumentType(DocumentType documentType) {
        try {
            documentType = getEm().merge(documentType);
            return documentType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByDocumentType(String documentTypeId) {
        try {
            getEm().remove(getEm().getReference(DocumentType.class, documentTypeId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public Emergency findByEmergencyId(BigDecimal emergencyId) {
        return getEm().find(Emergency.class, emergencyId);
    }

    public List<Emergency> findAllEmergency() {
        return getEm().createNamedQuery("Emergency.findAll", Emergency.class).getResultList();
    }

    public Emergency saveByEmergency(Emergency emergency) {
        try {
            emergency.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(emergency);
            return emergency;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Emergency updateByEmergency(Emergency emergency) {
        try {
            emergency = getEm().merge(emergency);
            return emergency;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByEmergency(String emergencyId) {
        try {
            getEm().remove(getEm().getReference(Emergency.class, emergencyId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public LeaveType findByLeaveTypeId(BigDecimal leaveTypeId) {
        return getEm().find(LeaveType.class, leaveTypeId);
    }

    public List<LeaveType> findAllLeaveType() {
        return getEm().createNamedQuery("LeaveType.findAll", LeaveType.class).getResultList();
    }

    public LeaveType saveByLeaveType(LeaveType leaveType) {
        try {
            leaveType.setId(SequenceUtil.nextBigDecimal());
            getEm().persist(leaveType);
            return leaveType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LeaveType updateByLeaveType(LeaveType leaveType) {
        try {
            leaveType = getEm().merge(leaveType);
            return leaveType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByLeaveType(BigDecimal leaveTypeId) {
        try {
            getEm().remove(getEm().getReference(LeaveType.class, leaveTypeId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//------------------------------------------------------------------------

    public WorkMonths findByWorkMonthsId(BigDecimal workMonthsId) {
        return getEm().find(WorkMonths.class, workMonthsId);
    }

    public List<WorkMonths> findAllWorkMonths() {
        return getEm().createNamedQuery("WorkMonths.findAll", WorkMonths.class).getResultList();
    }

    public WorkMonths saveByWorkMonths(WorkMonths workMonths) {
        try {
            workMonths.setId(SequenceUtil.nextBigDecimal());
            workMonths.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(workMonths);
            createEmployeeWorkMonth(workMonths);
            return workMonths;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createEmployeeWorkMonth(WorkMonths workMonths) {
        List<Employee> employees = getEm().createNamedQuery("Employee.findByIsActive", Employee.class)
                .setParameter("isActive", true).getResultList();
        for (Employee employee : employees) {
            saveByWorkMonths(employee, workMonths);
        }
    }

    private void saveByWorkMonths(Employee employee, WorkMonths workMonths) {
        EmployeeWorkMonth employeeWorkMonth = new EmployeeWorkMonth();
        employeeWorkMonth.setId(SequenceUtil.nextBigDecimal());
        employeeWorkMonth.setWorkMonthsid(workMonths);
        employeeWorkMonth.setWorkedHours(0);
        employeeWorkMonth.setFinalSalary(0d);
        employeeWorkMonth.setEmployeeCode(employee);
        getEm().persist(employeeWorkMonth);
    }

    public WorkMonths updateByWorkMonths(WorkMonths workMonths) {
        try {
            workMonths = getEm().merge(workMonths);
            return workMonths;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByWorkMonths(BigDecimal workMonthsId) {
        try {
            getEm().remove(getEm().getReference(WorkMonths.class, workMonthsId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<WorkMonths> findByIsLocked(boolean isLocked) {
        return getEm().createNamedQuery("WorkMonths.findByIsLocked", WorkMonths.class)
                .setParameter("isLocked", isLocked)
                .getResultList();
    }

    public WorkMonths findByYearAndMonth(int year, int month) {
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonth", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WorkMonths findByYearAndMonth() {
        DateTime dateTime = new DateTime();
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonth", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WorkMonths findByPreviousYearAndMonth(WorkMonths currentMonth) {
        int year;
        int month;
        if (currentMonth == null) {
            DateTime dateTime = new DateTime();
            year = dateTime.getYear();
            month = dateTime.getMonthOfYear() - 1;
        } else {
            year = currentMonth.getYear();
            month = currentMonth.getMonth() - 1;
        }
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonth", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WorkMonths findByNextYearAndMonth(WorkMonths currentMonth) {
        int year;
        int month;
        if (currentMonth == null) {
            DateTime dateTime = new DateTime();
            year = dateTime.getYear();
            month = dateTime.getMonthOfYear() + 1;
        } else {
            year = currentMonth.getYear();
            month = currentMonth.getMonth() + 1;
        }
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonth", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public WorkMonths findByYearAndMonthAndIsLocked(int year, int month, boolean isLocked) {
        try {
            return getEm().createNamedQuery("WorkMonths.findByYearAndMonthAndIsLocked", WorkMonths.class)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .setParameter("isLocked", isLocked)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//------------------------------------------------------------------------

    public Position findByPositionCode(String positionCode) {
        return getEm().find(Position.class, positionCode);
    }

    public List<Position> findAllPosition() {
        return getEm().createNamedQuery("Position.findAll", Position.class)
                .getResultList();
    }

    public Position saveByPosition(Position position) {
        try {
            position.setCreatedDate(Calendar.getInstance().getTime());
            getEm().persist(position);
            return position;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Position updateByPosition(Position position) {
        try {
            position = getEm().merge(position);
            return position;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteByPosition(String positionCode) {
        try {
            getEm().remove(getEm().getReference(Position.class, positionCode));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//----------------------Calendar POJO-----------------------------


    public List<CalendarPojo> getCalendarPojos() {
        WorkMonths workMonth = findByYearAndMonth();
        List<LeaveAbsence> leaveAbsences = leaveAbsenceBean.findByWorkMonths(workMonth);
        List<Overtime> overtimes = overtimeBean.findByWorkMonthsId(workMonth);

        List<CalendarPojo> calendarPojos = new ArrayList<>();

        for (LeaveAbsence leaveAbsence : leaveAbsences) {
            calendarPojos.add(
                    new CalendarPojo(
                            leaveAbsence.getEmployeeCode().getFirstname()
                                    + leaveAbsence.getLeaveTypeId().getLeaveType()
                            , DataTypeUtils.convertToDateString(leaveAbsence.getStartDate())
                            , DataTypeUtils.convertToDateString(leaveAbsence.getEndDate()))
            );
        }

        for (Overtime overtime : overtimes) {
            for (OvertimeDates overtimeDates : overtime.getOvertimeDatesList()) {
                String workDate = DataTypeUtils.convertToDateString(overtimeDates.getWorkDate());

                calendarPojos.add(
                        new CalendarPojo(
                                overtime.getEmployeeCode().getFullName() +
                                        overtime.getReason()
                                , workDate + "T" + overtimeDates.getStartTime()
                                , workDate + "T" + overtimeDates.getEndTime()
                        )
                );
            }
        }

        return calendarPojos;

    }

    public List<CalendarPojo> getCalendarPojos(WorkMonths workMonth) {
        List<LeaveAbsence> leaveAbsences = leaveAbsenceBean.findByWorkMonths(workMonth);
        List<Overtime> overtimes = overtimeBean.findByWorkMonthsId(workMonth);
        List<CalendarPojo> calendarPojos = new ArrayList<>();
        for (LeaveAbsence leaveAbsence : leaveAbsences) {
            calendarPojos.add(
                    new CalendarPojo(
                            leaveAbsence.getEmployeeCode().getFirstname()
                                    + leaveAbsence.getLeaveTypeId().getLeaveType()
                            , DataTypeUtils.convertToDateString(leaveAbsence.getStartDate())
                            , DataTypeUtils.convertToDateString(leaveAbsence.getEndDate()))
            );
        }

        for (Overtime overtime : overtimes) {
            for (OvertimeDates overtimeDates : overtime.getOvertimeDatesList()) {
                String workDate = DataTypeUtils.convertToDateString(overtimeDates.getWorkDate());

                calendarPojos.add(
                        new CalendarPojo(
                                overtime.getEmployeeCode().getFullName() +
                                        overtime.getReason()
                                , workDate + overtimeDates.getStartTime()
                                , workDate + overtimeDates.getEndTime()
                        )
                );
            }
        }

        return calendarPojos;
    }

//-----------------------Variables----------------------

    public List<Variables> findAllVariables() {
        return getEm().createNamedQuery("VARIABLES.findAll", Variables.class).getResultList();
    }

    public List<Variables> findVariableByIsActive(boolean isActive) {
        return getEm().createNamedQuery("VARIABLES.findByIsActive", Variables.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<Variables> findVariableByVariableCode(String variableCode) {
        return getEm().createNamedQuery("VARIABLES.findByVariableCode", Variables.class)
                .setParameter("variableCode", variableCode)
                .getResultList();
    }

    public List<Variables> findVariableByIsActiveAndVariableCode(String variableCode, boolean isActive) {
        return getEm().createNamedQuery("VARIABLES.findByVariableCodeAndIsActive", Variables.class)
                .setParameter("variableCode", variableCode)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    public List<Variables> findVariableByVariableName(String variableName) {
        return getEm().createNamedQuery("VARIABLES.findByVariableName", Variables.class)
                .setParameter("variableName", variableName)
                .getResultList();
    }

    public Variables findByVariableId(BigDecimal variableId) {
        return getEm().find(Variables.class, variableId);
    }

    public boolean updateVariables(List<Variables> variables) {
        try {
            for (Variables variable : variables) {
                getEm().merge(variable);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Variables updateVariables(Variables variable) {
        try {
            return getEm().merge(variable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
