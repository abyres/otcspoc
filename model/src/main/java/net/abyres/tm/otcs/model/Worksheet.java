package net.abyres.tm.otcs.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author onn
 */
@Entity
@Table(name = "C_WORKSHEET")
public class Worksheet {

    private String worksheetId;
    private Boolean isActive;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updatedBy;
    private String name;
    private String description;
    private LocalDateTime dateProcessed;

    @Id
    @Column(name = "C_WORKSHEET_ID")
    public String getWorksheetId() {
        return worksheetId;
    }

    public void setWorksheetId(String worksheetId) {
        this.worksheetId = worksheetId;
    }

    @Column(name = "IS_ACTIVE", nullable = false)
    @Convert(converter = BooleanToStringConverter.class)
    public Boolean isActive() {
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
        if (this.createdBy == null) {
            this.createdBy = updatedBy;
        }
        this.updatedBy = updatedBy;
    }

    @Column(name = "NAME", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "DATE_PROCESSED", nullable = false )
    public LocalDateTime getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(LocalDateTime dateProcessed) {
        this.dateProcessed = dateProcessed;
    }


}
