package com.ericsson.calculator;

import com.ericsson.model.CalculatorResult;
import com.ericsson.model.Item;
import com.ericsson.services.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculatorTests {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testTitle() {

        // 1 imported box of chocolates at 10.00
        // 1 imported bottle of perfume at 47.50

        List<Item> list = new ArrayList<>();
        list.add(new Item(1, true, true, "box of chocolates", new BigDecimal("10.00")));
        list.add(new Item(1, true, false, "imported bottle of perfume", new BigDecimal("47.50")));

        CalculatorResult result = calculator.calculate(list);

        Assertions.assertEquals(new BigDecimal("65.15"), result.getTotal());
        Assertions.assertEquals(new BigDecimal("7.65"), result.getSalesTaxes());

    }

    @Test
    public void testTotals() {

        // 2 box of Aspirine at 1.50
        // 3 imported book "Dune" at 15.50

        List<Item> list = new ArrayList<>();
        list.add(new Item(2, false, true, "box of Aspirine", new BigDecimal("1.50")));
        list.add(new Item(3, true, false, "book 'Dune'", new BigDecimal("15.50")));

        CalculatorResult result = calculator.calculate(list);

        Assertions.assertEquals(new BigDecimal("56.55"), result.getTotal());
        Assertions.assertEquals(new BigDecimal("7.05"), result.getSalesTaxes());

    }

    @Test
    public void testItems() {

        // 1 chess set at 25.00
        // 1 imported bottle of champagne at 105.00
        // 4 box of cookies at 7.65

        List<Item> list = new ArrayList<>();
        list.add(new Item(1, false, false, "chess set", new BigDecimal("25.00")));
        list.add(new Item(1, true, false, "imported bottle of champagne", new BigDecimal("105.00"))); // Alcohol is not tax exempted in this exercise. :)
        list.add(new Item(4, false, true, "box of cookies", new BigDecimal("7.65")));

        CalculatorResult result = calculator.calculate(list);

        // It's a list so it maintains order.

        Assertions.assertEquals(new BigDecimal("27.50"), result.getItems().get(0).getUnitPrice());
        Assertions.assertEquals(new BigDecimal("120.75"), result.getItems().get(1).getUnitPrice());
        Assertions.assertEquals(new BigDecimal("7.65"), result.getItems().get(2).getUnitPrice());

    }

}
