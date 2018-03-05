/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import net.abyres.tm.otcs.employee.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemusa.sidemenu.SideMenu;

/**
 *
 * @author onn
 */
@SpringUI
public class MainUI extends UI {
 private VerticalLayout root;

    private SideMenu sideMenu = new SideMenu();
    private String menuCaption = "OVERTIME CLAIM SYSTEM";

    @Autowired
    EmployeeView employeeView;

    @Override
    protected void init(VaadinRequest request) {
        setContent(sideMenu);
        Navigator navigator = new Navigator(this, sideMenu);
        setNavigator(navigator);

        navigator.addView("", employeeView);
        navigator.navigateTo("");

        sideMenu.setMenuCaption(menuCaption);
        sideMenu.addNavigation("Employee Management", "");
        sideMenu.addMenuItem(EmployeeView.VIEW_NAME, VaadinIcons.USERS, () -> {
            sideMenu.setContent(employeeView);
        });

        sideMenu.addNavigation("Overtime Management", "");
        // Arbitrary method execution
        sideMenu.addMenuItem("Overtime Element", VaadinIcons.STOCK, () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("Overtime Element View"));
            sideMenu.setContent(content);
        });
        sideMenu.addMenuItem("Overtime Scheduling", VaadinIcons.TIMER, () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("Overtime Scheduling View"));
            sideMenu.setContent(content);
        });
        sideMenu.addMenuItem("Reports", VaadinIcons.CHART_LINE, () -> {
            VerticalLayout content = new VerticalLayout();
            content.addComponent(new Label("Report View"));
            sideMenu.setContent(content);
        }).select();
    }
}
