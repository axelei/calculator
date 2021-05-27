package com.ericsson.services;

import com.ericsson.model.CalculatorResult;
import com.ericsson.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final static BigDecimal IMPORT_DUTY = new BigDecimal("0.05"); // Noted as percentages for easier multiplication.
    private final static BigDecimal SALE_TAXES = new BigDecimal("0.10");

    public CalculatorResult calculate(List<Item> items) {

        CalculatorResult result = new CalculatorResult();
        result.setItems(new ArrayList<>());

        for (Item item : items) {

            Item newItem = (Item) item.clone();

            BigDecimal importDuty = BigDecimal.ZERO;
            if (item.isExported()) {
                importDuty = percentageRound(item.getUnitPrice().multiply(IMPORT_DUTY));
            }

            BigDecimal taxes = BigDecimal.ZERO;
            if (!item.isTaxesExempt()) {
                taxes = item.getUnitPrice().multiply(SALE_TAXES);
                taxes = percentageRound(taxes);
            }

            BigDecimal price = item.getUnitPrice().add(taxes).add(importDuty);

            newItem.setUnitPrice(price);
            result.getItems().add(newItem);

            BigDecimal itemCount = BigDecimal.valueOf(item.getCount());

            result.setTotal(result.getTotal().add(price.multiply(itemCount)));
            result.setSalesTaxes(result.getSalesTaxes().add(taxes.add(importDuty).multiply(itemCount)));

        }

        return result;
    }

    /**
     * Rounds to nearest 5% percentage
     * @param number
     * @return rounded number
     */
    public BigDecimal percentageRound(BigDecimal number) {
        BigDecimal result = number;

        result = result.multiply(new BigDecimal("2"));
        result = result.setScale(1, RoundingMode.HALF_UP);
        result = result.divide(new BigDecimal("2"), 2, RoundingMode.HALF_UP);

        return result;
    }

}
