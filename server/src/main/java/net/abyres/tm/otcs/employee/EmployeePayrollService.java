/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.employee;

import java.util.List;
import javafx.collections.MapChangeListener;
import net.abyres.tm.otcs.model.EmployeePayrollElement;

/**
 *
 * @author onn
 */
public interface EmployeePayrollService {

    // CRUD
    public EmployeePayrollElement addIncome(EmployeePayrollElement element);
    public EmployeePayrollElement removeIncome(EmployeePayrollElement element);
    public EmployeePayrollElement addDeduction(EmployeePayrollElement element);
    public EmployeePayrollElement removeDeduction(EmployeePayrollElement element);

    // Listeners
    public void addIncomeListener(MapChangeListener<String, EmployeePayrollElement> listener);
    public void removeIncomeListener(MapChangeListener<String, EmployeePayrollElement> listener);
    public void addDeductionListener(MapChangeListener<String, EmployeePayrollElement> listener);
    public void removeDeductionListener(MapChangeListener<String, EmployeePayrollElement> listener);

    // Data related
    public List<EmployeePayrollElement> getAllIncomes();
    public List<EmployeePayrollElement> getAllDeductions();
}
