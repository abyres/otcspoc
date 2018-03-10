/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.dashboard;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.RadarChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.RadarDataset;
import com.byteowls.vaadin.chartjs.options.scale.RadialLinearScale;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
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
    private TabSheet tabSheet;

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

        HorizontalLayout layout = new HorizontalLayout(radarSkippePoint());
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

    private Component radarSkippePoint() {
        RadarChartConfig config = new RadarChartConfig();
        config
                .data()
                .labels("Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running")
                .addDataset(new RadarDataset().label("Skip first dataset").borderColor("rgb(255, 0, 0)")
                        .backgroundColor("rgba(255,255,0,0.5)").pointBackgroundColor("rgba(220,220,220,1)"))
                .addDataset(new RadarDataset().label("Skip mid dataset").borderColor("rgb(255, 0, 255)")
                        .backgroundColor("rgba(0, 255, 0, 0.5)").pointBackgroundColor("rgba(151,187,205,1)").pointHoverBackgroundColor("#fff"))
                .addDataset(new RadarDataset().label("Skip last dataset").borderColor("rgb(0, 255, 255)")
                        .backgroundColor("rgba(0, 0, 255, 0.5)").pointBackgroundColor("rgba(151,187,205,1)").pointHoverBackgroundColor("#fff"))
                .and();

        config.
                options()
                .title()
                .display(true)
                .text("Chart.js Radar Chart - Skip Points")
                .and()
                .elements()
                .line()
                .tension(0.0)
                .and()
                .and()
                .scale(new RadialLinearScale().ticks().beginAtZero(true).and().reverse(false))
                .done();

        List<String> labels = config.data().getLabels();
        int cnt = 0;
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            RadarDataset lds = (RadarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                if ((cnt == 0 && i == 0) || (cnt == 1 && i == 3) || (cnt == 2 && i == 6)) {
                    data.add(Double.NaN);
                } else {
                    data.add((double) (Math.round(Math.random() * 100)));
                }
            }
            lds.dataAsList(data);
            cnt++;
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
//        chart.addClickListener((a, b)
//                -> DemoUtils.notification(a, b, config.data().getDatasets().get(a)));
        return chart;

    }

}
