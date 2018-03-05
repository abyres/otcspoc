
package net.abyres.tm.otcs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represent Product as payroll element.
 *
 * @author onn
 */
@Entity
@Table(name = "A_PAYROLL_ELEMENT")
public class PayrollElement {
    private String payrollElementId;
    private Boolean isActive;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
    private String productId;
    private String description;
    private String name;
    private BigDecimal line;
    private Boolean income;

    public PayrollElement() {
        this.isActive = true;
        this.income = true;
    }

    @Id
    @Column(name = "A_PAYROLL_ELEMENT_ID")
    public String getPayrollElementId() {
        return payrollElementId;
    }

    public void setPayrollElementId(String payrollElementId) {
        this.payrollElementId = payrollElementId;
    }

    @Column(name = "A_PRODUCT_ID", nullable = false)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "LINE", nullable = false)
    public BigDecimal getLine() {
        return line;
    }

    public void setLine(BigDecimal line) {
        this.line = line;
    }

    @Column(name = "IS_INCOME", nullable = false)
    @Convert(converter=BooleanToStringConverter.class)
    public Boolean isIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }


}
