package com.man.cleanup.data;

import java.util.Objects;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity( name = "tasks")
public class Task extends PanacheEntityBase {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String guidelines;

    @Column
    private boolean active = true;

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
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the active
     */
    public boolean isAtive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}