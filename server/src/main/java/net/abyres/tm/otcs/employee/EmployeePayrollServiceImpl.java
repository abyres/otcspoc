/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.employee;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javax.persistence.EntityManager;
import net.abyres.tm.otcs.model.EmployeePayrollElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author onn
 */
@Service
public class EmployeePayrollServiceImpl implements EmployeePayrollService {

    @Autowired
    EntityManager entityManager;

    ObservableMap<String, EmployeePayrollElement> observableIncomeMap = FXCollections.observableHashMap();
    ObservableMap<String, EmployeePayrollElement> observableDeductionMap = FXCollections.observableHashMap();

    @Override
    public EmployeePayrollElement addIncome(EmployeePayrollElement element) {
        return observableIncomeMap.put(element.getEmployeePayrollElementId(), element);
    }

    @Override
    public EmployeePayrollElement removeIncome(EmployeePayrollElement element) {
        return observableIncomeMap.remove(element.getEmployeePayrollElementId());
    }

    @Override
    public EmployeePayrollElement addDeduction(EmployeePayrollElement element) {
        return observableDeductionMap.put(element.getEmployeePayrollElementId(), element);
    }

    @Override
    public EmployeePayrollElement removeDeduction(EmployeePayrollElement element) {
        return observableDeductionMap.remove(element.getEmployeePayrollElementId());
    }

    @Override
    public void addIncomeListener(MapChangeListener<String, EmployeePayrollElement> listener) {
        observableIncomeMap.addListener(listener);
    }

    @Override
    public void removeIncomeListener(MapChangeListener<String, EmployeePayrollElement> listener) {
        observableIncomeMap.removeListener(listener);
    }

    @Override
    public void addDeductionListener(MapChangeListener<String, EmployeePayrollElement> listener) {
        observableDeductionMap.addListener(listener);
    }

    @Override
    public void removeDeductionListener(MapChangeListener<String, EmployeePayrollElement> listener) {
        observableDeductionMap.removeListener(listener);
    }

    @Override
    public List<EmployeePayrollElement> getAllIncomes() {
        List<EmployeePayrollElement> copyList = new ArrayList<>();
        if (!observableIncomeMap.values().isEmpty()) {
            observableIncomeMap.values().stream().forEach(p -> copyList.add(cloneIncome(p)));
        } else {
            entityManager.createQuery("from EmployeePayrollElement where income=true", EmployeePayrollElement.class).getResultList().stream()
                    .forEach(b -> {
                        observableIncomeMap.put(b.getEmployeePayrollElementId(), cloneIncome(b));
                        copyList.add(cloneIncome(b));
                    });
        }

        return copyList;
    }

    @Override
    public List<EmployeePayrollElement> getAllDeductions() {
        List<EmployeePayrollElement> copyList = new ArrayList<>();
        if (!observableDeductionMap.values().isEmpty()) {
            observableDeductionMap.values().stream().forEach(p -> copyList.add(cloneDeduction(p)));
        } else {
            entityManager.createQuery("from EmployeePayrollElement where income=false", EmployeePayrollElement.class).getResultList().stream()
                    .forEach(b -> {
                        observableDeductionMap.put(b.getEmployeePayrollElementId(), cloneDeduction(b));
                        copyList.add(cloneDeduction(b));
                    });
        }

        return copyList;
    }

    private EmployeePayrollElement cloneIncome(EmployeePayrollElement p) {
        return cloneElement(p);
    }

    private EmployeePayrollElement cloneDeduction(EmployeePayrollElement b) {
        return cloneElement(b);
    }

    private EmployeePayrollElement cloneElement(EmployeePayrollElement b) {
        EmployeePayrollElement element = new EmployeePayrollElement();
        element.setActive(b.isActive());
        element.setAmount(b.getAmount());
        element.setCreated(b.getCreated());
        element.setCreatedBy(b.getCreatedBy());
        element.setIncome(b.isIncome());
        element.setLine(b.getLine());
        element.setEmployeePayrollElementId(b.getEmployeePayrollElementId());
        element.setProductId(b.getProductId());
        element.setUpdated(b.getUpdated());
        element.setUpdatedBy(b.getUpdatedBy());
        return element;
    }

}
