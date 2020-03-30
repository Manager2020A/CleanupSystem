package com.man.cleanup.data;

import java.util.Objects;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name = "cleaning_tasks")
public class CleaningTask extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ref_task", insertable = false, updatable = false )
    private Task task;

    @Basic
    private int ref_task;

    @Column(name = "ref_cleaning")
    private int cleaningId;

    @Column
    private boolean realized = true;

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param realized the realized to set
     */
    public void setRealized(boolean realized) {
        this.realized = realized;
    }

    /**
     * @return the cleaningId
     */
    public int getCleaningId() {
        return cleaningId;
    }

    /**
     * @param cleaningId the cleaningId to set
     */
    public void setCleaningId(int cleaningId) {
        this.cleaningId = cleaningId;
    }

    /**
     * @return the realized
     */
    public boolean isRealized() {
        return realized;
    }

    
    public void setTaskId( int task )
    {
        this.ref_task = task;
    }

    public int getTaskId()
    {
        return ref_task;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CleaningTask cleaningTask = (CleaningTask) o;
        return Objects.equals(id, cleaningTask.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}