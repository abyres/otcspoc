package net.abyres.tm.otcs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Payroll processing line item
 *
 * @author onn
 */
@Entity
@Table(name = "C_WORKSHEET_LINE")
public class WorksheetLine {

    private String worksheetLineId;
    private String worksheetId;
    private String payrollElementId;
    private Boolean isActive;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
    private String productId;
    private String bpartnerId;
    private LocalDateTime validFrom;
    private BigDecimal line;
    private Boolean income;
    private BigDecimal amount;

    public WorksheetLine() {
        isActive = true;
        income = true;
        amount = BigDecimal.ZERO;
    }

    /**
     * Constructor to create virtual records.
     *
     * The virtual record is normally a cross join of PayrollElement and
     * Employee class
     */
    public WorksheetLine(String payrollElementId, String bPartnerId, Boolean income, BigDecimal amount) {
        isActive = true;
        this.payrollElementId = payrollElementId;
        this.bpartnerId = bPartnerId;
        this.income = income;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                @Parameter (
                        name = "uuid_gen_strategy_class",
                        value = "org.hibernate.uuid.CustomVersionOneStrategy")})
    @Column(name = "C_WORKSHEET_LINE_ID")
    public String getWorksheetLineId() {
        return worksheetLineId;
    }

    public void setWorksheetLineId(String payrollElementId) {
        this.worksheetLineId = payrollElementId;
    }

    @Column(name = "C_WORKSHEET_ID", nullable = false)
    public String getWorksheetId() {
        return worksheetId;
    }

    public void setWorksheetId(String worksheetId) {
        this.worksheetId = worksheetId;
    }

    @Column(name = "A_PAYROLL_ELEMENT_ID", nullable = false)
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
    @Convert(converter = BooleanToStringConverter.class)
    public Boolean isActive() {
        if (isActive == null) {
            isActive = Boolean.TRUE;
        }
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
        if (this.created == null) {
            this.created = updated;
        }
        this.updated = updated;
    }

    @Column(name = "UPDATEDBY", nullable = false)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        if (this.createdBy == null) {
            this.createdBy = updatedBy;
        }
        this.updatedBy = updatedBy;
    }

    @Column(name = "LINE", nullable = false)
    public BigDecimal getLine() {
        return line;
    }

    public void setLine(BigDecimal line) {
        this.line = line;
    }

    @Column(name = "IS_INCOME", nullable = false)
    @Convert(converter = BooleanToStringConverter.class)
    public Boolean isIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    @Column(name = "AMOUNT", nullable = false)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "A_BPARTNER_ID", nullable = false)
    public String getBpartnerId() {
        return bpartnerId;
    }

    public void setBpartnerId(String bpartnerId) {
        this.bpartnerId = bpartnerId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.payrollElementId);
        hash = 67 * hash + Objects.hashCode(this.bpartnerId);
        hash = 67 * hash + Objects.hashCode(this.validFrom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorksheetLine other = (WorksheetLine) obj;
        if (!Objects.equals(this.payrollElementId, other.payrollElementId)) {
            return false;
        }
        if (!Objects.equals(this.bpartnerId, other.bpartnerId)) {
            return false;
        }
        if (!Objects.equals(this.validFrom, other.validFrom)) {
            return false;
        }
        return true;
    }

}
