/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author onn
 */

@Entity
@Table(name = "A_BPARTNER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BPTYPE")
public class BusinessPartner {

    private String bpartnerId;
    private String bpartnerCategoryId;
    private Boolean isActive;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
    private String value;
    private String name;
    private String description;
    private String taxid;

    @Id
    @Column(name = "A_BPARTNER_ID")
    public String getBpartnerId() {
        return bpartnerId;
    }

    public void setBpartnerId(String bpartnerId) {
        this.bpartnerId = bpartnerId;
    }

    @Column(name = "A_BPARTNER_CATEGORY_ID", nullable = false)
    public String getBpartnerCategoryId() {
        return bpartnerCategoryId;
    }

    public void setBpartnerCategoryId(String bpartnerCategoryId) {
        this.bpartnerCategoryId = bpartnerCategoryId;
    }

    @Column(name = "IS_ACTIVE", nullable = false)
    @Convert(converter=BooleanToStringConverter.class)
    public Boolean isActive() {
        if (isActive == null)
            isActive = Boolean.TRUE;
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Column(name = "CREATED", nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "CREATEDBY", nullable = false)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "UPDATED", nullable = false)
    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        if (this.created == null)
            this.created = updated;
        this.updated = updated;
    }

    @Column(name = "UPDATEDBY", nullable = false)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        if (this.createdBy == null)
            this.createdBy = updatedBy;
        this.updatedBy = updatedBy;
    }

    @Column(name = "VALUE", length=40, nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "NAME", length=255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", length=255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "TAXID", length=20)
    public String getTaxid() {
        return taxid;
    }

    public void setTaxid(String taxid) {
        this.taxid = taxid;
    }


}
