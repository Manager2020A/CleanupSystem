package com.man.cleanup.data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name = "cleanings")
public class Cleaning extends PanacheEntityBase {

    public enum Frequency {
        MANUAL, DAY, WEEKLY, BI_WEEKLY, MONTH, YEAR,
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String guidelines;

    @Column(name = "next_date")
    private LocalDate nextDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "estimated_time")
    private LocalTime estimateTime;

    @Column
    private boolean active = true;

    @Enumerated
    @Column
    private Frequency frequency = Frequency.MANUAL;

    @OneToMany()
    @JoinColumn(name = "ref_cleaning")
    private List<CleaningTask> tasks = new ArrayList<CleaningTask>();

    @OneToMany()
    @JoinColumn(name = "ref_cleaning")
    private List<CleaningProduct> products = new ArrayList<CleaningProduct>();

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
     * @return the nextDate
     */
    public LocalDate getNextDate() {
        return nextDate;
    }

    /**
     * @param nextDate the nextDate to set
     */
    public void setNextDate(LocalDate nextDate) {
        this.nextDate = nextDate;
    }

    /**
     * @return the estimateTime
     */
    public LocalTime getEstimateTime() {
        return estimateTime;
    }

    /**
     * @param estimateTime the estimateTime to set
     */
    public void setEstimateTime(LocalTime estimateTime) {
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
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the frequency
     */
    public Frequency getFrequency() {
        return frequency;
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

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<CleaningTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the tasks
     */
    public List<CleaningTask> getTasks() {
        return tasks;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<CleaningProduct> products) {
        this.products = products;
    }

    /**
     * @return the products
     */
    public List<CleaningProduct> getProducts() {
        return products;
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