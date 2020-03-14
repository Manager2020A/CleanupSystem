package com.man.cleanup.data;

import java.util.Objects;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity( name = "products")
public class Product extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String branding;

    @Column
    private double capacity;

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
     * @return the branding
     */
    public String getBranding() {
        return branding;
    }

    /**
     * @param branding the branding to set
     */
    public void setBranding(String branding) {
        this.branding = branding;
    }

    /**
     * @return the capacity
     */
    public double getCapacity() {
        return capacity;
    }
    
    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * @param 
     * 
     *  the active to set
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
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}