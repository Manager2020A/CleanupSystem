package com.man.cleanup.data;

import java.util.Objects;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name = "cleaning_products")
public class CleaningProduct extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ref_product")
    private Product product;

    @Column(name = "ref_cleaning")
    private int cleaningId;

    @Column
    private double amount;

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
     * @return the product
     */
    public Product getProduct() {
        return product;
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

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CleaningProduct cleaningProduct = (CleaningProduct) o;
        return Objects.equals(id, cleaningProduct.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}