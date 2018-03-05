/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.employee;

import java.util.List;
import javafx.collections.MapChangeListener;
import net.abyres.tm.otcs.model.BusinessPartnerCategory;
import net.abyres.tm.otcs.model.Employee;

/**
 *
 * @author onn
 */
public interface EmployeeService {

    void addEmployee(Employee bp);

    void addListener(MapChangeListener<? super String, ? super Employee> listener);

    Employee cloneEmployee(Employee p);

    List<BusinessPartnerCategory> getAllBusinessPartnerCategory();

    List<Employee> getAllEmployee();

    BusinessPartnerCategory getBusinessPartnerCategoryFromId(String bpartnerCategoryId);

    Employee getEmployeeFromId(String employeeId);

    void removeListener(MapChangeListener<? super String, ? super Employee> listener);

    /**
     * Find a single employee record by name or value case insensitive.
     *
     * @param newValue
     * @return a single record or null if not found
     */
    public Employee findEmployeeByText(String newValue);

    public Employee findEmployeeById(String bpartnerId);

}
