package com.ericsson.model;

import java.math.BigDecimal;

public class Item implements Cloneable {

    private int count = 1;
    private boolean exported = false;
    private boolean taxesExempt = false;
    private String name;
    private BigDecimal unitPrice; // Money shouldn't be float/double because IEEE format rounding errors.

    public Item() {}

    public Item(int count, boolean exported, boolean taxesExempt, String name, BigDecimal unitPrice) {
        this.count = count;
        this.exported = exported;
        this.name = name;
        this.unitPrice = unitPrice;
        this.taxesExempt = taxesExempt;
    }

    @Override
    public Object clone() {
        return new Item(count, exported, taxesExempt, name, unitPrice);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isTaxesExempt() {
        return taxesExempt;
    }

    public void setTaxesExempt(boolean taxesExempt) {
        this.taxesExempt = taxesExempt;
    }

    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
