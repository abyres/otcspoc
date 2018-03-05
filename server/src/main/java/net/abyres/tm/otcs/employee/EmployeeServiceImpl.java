package net.abyres.tm.otcs.employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.abyres.tm.otcs.model.BusinessPartnerCategory;
import net.abyres.tm.otcs.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author onn
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @PersistenceContext
    EntityManager entityManager;

    private final ObservableMap<String, Employee> observableMap
            = FXCollections.observableHashMap();
    private final ObservableMap<String, BusinessPartnerCategory> observableBusinessPartnerCategoryMap = FXCollections.observableHashMap();
    private BusinessPartnerCategory defaultBpartnerCategory;

    @Override
    public void addListener(MapChangeListener<? super String, ? super Employee> listener) {
        observableMap.addListener(listener);
    }

    @Override
    public void removeListener(MapChangeListener<? super String, ? super Employee> listener) {
        observableMap.removeListener(listener);
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> copyList = new ArrayList<>();
        if (!observableMap.values().isEmpty()) {
            observableMap.values().stream().forEach(p -> copyList.add(cloneEmployee(p)));
        } else {
            entityManager.createQuery("from Employee", Employee.class).getResultList().stream()
                    .forEach(b -> {
                        observableMap.put(b.getBpartnerId(), cloneEmployee(b));
                        copyList.add(cloneEmployee(b));
                    });
        }

        return copyList;
    }

    @Override
    public List<BusinessPartnerCategory> getAllBusinessPartnerCategory() {
        List<BusinessPartnerCategory> copyList = new ArrayList<>();
        if (!observableBusinessPartnerCategoryMap.isEmpty()) {
            observableBusinessPartnerCategoryMap.values().stream().forEach(p -> copyList.add(cloneEmployeeCategory(p)));
        } else {
            entityManager.createQuery("from BusinessPartnerCategory", BusinessPartnerCategory.class).getResultList().stream()
                    .forEach(p -> {
                        observableBusinessPartnerCategoryMap.put(p.getBPartnerCategoryId(), cloneEmployeeCategory(p));
                        copyList.add(cloneEmployeeCategory(p));
                    });
        }

        return copyList;
    }

    @Override
    public Employee cloneEmployee(Employee p) {
        Employee bpartner = new Employee();
        bpartner.setBpartnerCategoryId(p.getBpartnerCategoryId());
        bpartner.setBpartnerId(p.getBpartnerId());
        bpartner.setCreated(p.getCreated());
        bpartner.setCreatedBy(p.getCreatedBy());
        bpartner.setDescription(p.getDescription());
        bpartner.setActive(p.isActive());
        bpartner.setName(p.getName());
        bpartner.setTaxid(p.getTaxid());
        bpartner.setUpdated(p.getUpdated());
        bpartner.setUpdatedBy(p.getUpdatedBy());
        bpartner.setValue(p.getValue());
        bpartner.setDateJoined(p.getDateJoined());
        return bpartner;
    }

    @Transactional
    @Override
    public void addEmployee(Employee bp) {
        Employee newbp = cloneEmployee(bp);

        newbp.setUpdated(LocalDateTime.now());
        newbp.setUpdatedBy(getCurrentUserId());

        entityManager.merge(newbp);
        observableMap.put(newbp.getBpartnerId(), newbp);
    }

    private String getCurrentUserId() {
        return "00000000-0000-0000-0000-000000000100";
    }

    private BusinessPartnerCategory cloneEmployeeCategory(BusinessPartnerCategory p) {
        BusinessPartnerCategory bpcat = new BusinessPartnerCategory();
        bpcat.setActive(p.isActive());
        bpcat.setBPartnerCategoryId(p.getBPartnerCategoryId());
        bpcat.setCreated(p.getCreated());
        bpcat.setCreatedBy(p.getCreatedBy());
        bpcat.setDefault(p.isDefault());
        bpcat.setDescription(p.getDescription());
        bpcat.setName(p.getName());
        bpcat.setUpdated(p.getUpdated());
        bpcat.setUpdatedBy(p.getUpdatedBy());
        bpcat.setValue(p.getValue());
        return bpcat;
    }

    @Override
    public BusinessPartnerCategory getBusinessPartnerCategoryFromId(String bpartnerCategoryId) {
        if (bpartnerCategoryId == null || bpartnerCategoryId.isEmpty()) {
            return getDefaultEmployeeCategory();
        }

        return entityManager.find(BusinessPartnerCategory.class, bpartnerCategoryId);
    }

    private BusinessPartnerCategory getDefaultEmployeeCategory() {
        if (defaultBpartnerCategory == null) {
            defaultBpartnerCategory = entityManager.createQuery("from BusinessPartnerCategory where isDefault=TRUE",
                    BusinessPartnerCategory.class)
                    .getSingleResult();
        }
        return defaultBpartnerCategory;
    }

    @Override
    public Employee getEmployeeFromId(String employeeId) {
        if (employeeId == null || employeeId.isEmpty()) {
            return null;
        }

        return entityManager.find(Employee.class, employeeId);
    }

    @Override
    public Employee findEmployeeByText(String newValue) {
        List<Employee> ee
                = entityManager.createQuery("from Employee"
                        + " where lower(value) like lower(:val)"
                        + " or lower(name) like lower(:val)", Employee.class)
                        .setParameter("val", "%" + newValue + "%")
                        .getResultList();
        if (ee.isEmpty()) {
            return null;
        } else {
            return ee.get(0);
        }
    }

    @Override
    public Employee findEmployeeById(String bpartnerId) {
        List<Employee> ee
                = entityManager.createQuery("from Employee"
                        + " where bpartnerId = :val", Employee.class)
                        .setParameter("val", bpartnerId)
                        .getResultList();
        if (ee.isEmpty()) {
            return null;
        } else {
            return ee.get(0);
        }
    }
}
