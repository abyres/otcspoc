/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.bean;

import com.canoo.platform.remoting.DolphinBean;
import com.canoo.platform.remoting.Property;
import java.time.LocalDateTime;

@DolphinBean
public class EmployeeModel {

    private Property<String> bpartnerId;
    private Property<String> bpartnerCategoryId;
    private Property<Boolean> isActive;
    private Property<LocalDateTime> created;
    private Property<String> createdBy;
    private Property<LocalDateTime> updated;
    private Property<LocalDateTime> dateJoined;
    private Property<String> updatedBy;
    private Property<String> value;
    private Property<String> name;
    private Property<String> description;
    private Property<String> taxid;

    /* Property Access */

    public Property<String> getBpartnerIdProperty() {
        return bpartnerId;
    }

    public Property<String> getBpartnerCategoryIdProperty() {
        return bpartnerCategoryId;
    }

    public Property<Boolean> getIsActiveProperty() {
        return isActive;
    }

    public Property<LocalDateTime> getCreatedProperty() {
        return created;
    }

    public Property<String> getCreatedByProperty() {
        return createdBy;
    }

    public Property<LocalDateTime> getUpdatedProperty() {
        return updated;
    }

    public Property<LocalDateTime> getDateJoinedProperty() {
        return dateJoined;
    }

    public Property<String> getUpdatedByProperty() {
        return updatedBy;
    }

    public Property<String> getValueProperty() {
        return value;
    }

    public Property<String> getNameProperty() {
        return name;
    }

    public Property<String> getDescriptionProperty() {
        return description;
    }

    public Property<String> getTaxidProperty() {
        return taxid;
    }


}
