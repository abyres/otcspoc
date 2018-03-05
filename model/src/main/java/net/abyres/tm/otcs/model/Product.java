/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.abyres.tm.otcs.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product item model
 *
 * @author onn
 */
@Entity
@Table(name = "A_PRODUCT")
public class Product implements Serializable {

    private String productId;
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;
    private Boolean isActive;
    private String value;
    private String name;
    private String name2;
    private String description;
    private String upc;
    private String uom;
    private String productCategoryId;
    private String taxCategoryId;
    private Boolean isDiscontinued;
    private LocalDateTime discontinuedBy;

    @Id
    @Column(name = "A_PRODUCT_ID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "VALUE", length=40, nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "DESCRIPTION", length=255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "CREATED", nullable = false)
    public LocalDateTime getCreated() {
        if (created == null) {
            created = LocalDateTime.now();
        }
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "UPDATED", nullable = false)
    public LocalDateTime getUpdated() {
        if (updated == null) {
            updated = LocalDateTime.now();
        }
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        if (created == null)
            created = updated;
        this.updated = updated;
    }

    @Column(name = "NAME", length=255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CREATEDBY", nullable = false)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "UPDATEDBY", nullable = false)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        if (createdBy == null)
            createdBy = updatedBy;
        this.updatedBy = updatedBy;
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

    @Column(name = "NAME2", length=255)
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Column(name = "UPC", length=30)
    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    @Column(name = "UOM", length=10, nullable = false)
    public String getUom() {
        if (uom == null || uom.isEmpty())
            uom = "EA";
        return uom;
    }

    /**
     * Current UOM setup is ad-hoc user definable UOM at product master screen.
     *
     * @param uom
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    @Column(name = "A_PRODUCT_CATEGORY_ID", nullable = false)
    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Column(name = "A_TAX_CATEGORY_ID", nullable = false)
    public String getTaxCategoryId() {
        return taxCategoryId;
    }

    public void setTaxCategoryId(String taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
    }

    @Column(name = "IS_DISCONTINUED", nullable = false)
    @Convert(converter=BooleanToStringConverter.class)
    public Boolean isDiscontinued() {
        if (isDiscontinued == null)
            isDiscontinued = Boolean.FALSE;
        return isDiscontinued;
    }

    public void setDiscontinued(Boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }

    @Column(name = "DISCONTINUEDBY")
    public LocalDateTime getDiscontinuedBy() {
        return discontinuedBy;
    }

    public void setDiscontinuedBy(LocalDateTime discontinuedBy) {
        this.discontinuedBy = discontinuedBy;
    }

}
