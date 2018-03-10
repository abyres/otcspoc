/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.dashboard;

import net.abyres.tm.otcs.employee.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.abyres.tm.otcs.model.BusinessPartnerCategory;
import net.abyres.tm.otcs.model.Employee;
import net.abyres.tm.otcs.model.EmployeePayrollElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *
 * @author onn
 */
@UIScope
@SpringView(name = DashboardView.VIEW_NAME)
public class DashboardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "Dashboard View";

    private static final Logger logger = Logger.getLogger(DashboardView.class.getName());

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeePayrollService employeePayrollService;
    @Autowired
    EntityManager entityManager; // not good to have entity manager here

    private Employee theEmployee = null;
    private Boolean isDirty = Boolean.FALSE;

    List<Employee> tableViewExplorerData = new ArrayList<>();

    private Grid<Employee> tableViewExplorer;
    private TextField textFieldRecordId;
    private TextField textFieldValue;
    private TextField textFieldName;
    private ComboBox<BusinessPartnerCategory> comboBoxCategory;
    private TextArea textAreaDescription;
    private DateField dateJoined;
    private TextField textFieldSearch;
    private Button buttonSave;
    private Button buttonCreate;
    private Grid<EmployeePayrollElement> tableViewIncome;
    private Grid<EmployeePayrollElement> tableViewDeduction;
    private VerticalLayout tabPayrollInfo;

    public DashboardView() {


    }

    // Header
    private Component header() {
        buttonSave = new Button("Save", VaadinIcons.DISC);
        buttonCreate = new Button("New", VaadinIcons.STAR);
        HorizontalLayout layout = new HorizontalLayout(buttonSave, buttonCreate);
        return layout;
    }
    // Body
    private Component body() {

        textFieldRecordId = new TextField("Record ID");
        textFieldValue = new TextField("Code");
        textFieldName = new TextField("Name");
        comboBoxCategory = new ComboBox<>("Category");
        textAreaDescription = new TextArea("Description");
        dateJoined = new DateField("Joined");


        // Create form
        Panel pnl = new Panel("Employee Information");
        GridLayout formPane = new GridLayout(2, 7);
        formPane.setWidth("400px");
        formPane.setMargin(true);
        formPane.addComponent(textFieldRecordId, 0, 0);
        formPane.addComponent(textFieldValue, 0, 1);
        formPane.addComponent(textFieldName, 0, 2, 1, 2);
        formPane.addComponent(comboBoxCategory, 0, 3);
        formPane.addComponent(textAreaDescription, 0, 4, 1, 5);
        formPane.addComponent(dateJoined, 0, 6);
        pnl.setContent(formPane);

        tableViewExplorer = new Grid<>("Registered Employee");
        /*Retrieving employee data*/
        tableViewExplorer.setItems(employeeService.getAllEmployee());
        tableViewExplorer.addColumn(Employee::getValue).setCaption("No.");
        tableViewExplorer.addColumn(Employee::getName).setCaption("Name");

        textFieldSearch = new TextField("Search");
        VerticalLayout explorerPane = new VerticalLayout(textFieldSearch, tableViewExplorer);
        HorizontalLayout layout = new HorizontalLayout(explorerPane, pnl);
        return layout;
    }
    // Footer
//    private Component footer() {
//        HorizontalLayout layout = new HorizontalLayout(tableViewExplorer);
//        return layout;
//    }

    @Override
    public void enter(ViewChangeEvent event) {
        addComponent(new Label("Employee View"));
        addComponent(header());
        addComponent(body());
//        addComponent(footer());
    }

}
