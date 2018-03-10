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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.abyres.tm.otcs.model.BusinessPartnerCategory;
import net.abyres.tm.otcs.model.Employee;
import net.abyres.tm.otcs.model.EmployeePayrollElement;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
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

        tabSheet = new TabSheet();
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                com.vaadin.ui.JavaScript
                        .eval("setTimeout(function(){prettyPrint();},300);");
            }
        });
        tabSheet.setSizeFull();
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        HorizontalLayout layout = new HorizontalLayout(tabSheet);
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

    /**
     * Updates main tabSheet
     * <p>
     * Adds one tab with one example instance, one with the java source and
     * another one with html source in case of declarative example
     *
     * @param chartExample
     */
    private void updateTabSheet(Class chartExample) {
        try {
            tabSheet.removeAllComponents();

            DataSeries dataSeries = new DataSeries()
                    .add(2, 6, 7, 10);

            SeriesDefaults seriesDefaults = new SeriesDefaults()
                    .setRenderer(SeriesRenderers.BAR);

            Axes axes = new Axes()
                    .addAxis(
                            new XYaxis()
                                    .setRenderer(AxisRenderers.CATEGORY)
                                    .setTicks(
                                            new Ticks()
                                                    .add("a", "b", "c", "d")));

            Highlighter highlighter = new Highlighter()
                    .setShow(false);

            Options options = new Options()
                    .setSeriesDefaults(seriesDefaults)
                    .setAxes(axes)
                    .setHighlighter(highlighter);

            DCharts chart = new DCharts()
                    .setDataSeries(dataSeries)
                    .setOptions(options)
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
