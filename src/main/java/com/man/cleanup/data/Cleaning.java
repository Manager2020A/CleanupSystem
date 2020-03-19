package com.man.cleanup.data;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

//@Entity(name = "cleanings")
public class Cleaning extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String guidelines;

    @Column(name = "estimated_date")
    private Date estimateDate;

    @Column(name = "realized_date")
    private Date realizedDate;

    @Column(name = "estimate_time")
    private Time estimateTime;

    @Column
    private boolean active;

    ;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the estimateDate
     */
    public Date getEstimateDate() {
        return estimateDate;
    }

    /**
     * @param estimateDate the estimateDate to set
     */
    public void setEstimateDate(Date estimateDate) {
        this.estimateDate = estimateDate;
    }

    /**
     * @return the estimateTime
     */
    public Time getEstimateTime() {
        return estimateTime;
    }

    /**
     * @param estimateTime the estimateTime to set
     */
    public void setEstimateTime(Time estimateTime) {
        this.estimateTime = estimateTime;
    }

    /**
     * @return the guidelines
     */
    public String getGuidelines() {
        return guidelines;
    }

    /**
     * @param guidelines the guidelines to set
     */
    public void setGuidelines(String guidelines) {
        this.guidelines = guidelines;
    }

    /**
     * @return the realizedDate
     */
    public Date getRealizedDate() {
        return realizedDate;
    }

    /**
     * @param realizedDate the realizedDate to set
     */
    public void setRealizedDate(Date realizedDate) {
        this.realizedDate = realizedDate;
    }

    /**
     * @param
     * 
     * the        active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cleaning cleaning = (Cleaning) o;
        return Objects.equals(id, cleaning.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}