package com.ericsson.model;

import java.math.BigDecimal;
import java.util.List;

public class CalculatorResult {
    private List<Item> items;
    private BigDecimal salesTaxes = new BigDecimal(0);
    private BigDecimal total = new BigDecimal(0);

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
