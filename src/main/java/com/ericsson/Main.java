package com.ericsson;

import com.ericsson.model.CalculatorResult;
import com.ericsson.model.Item;
import com.ericsson.services.Calculator;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String[] EXEMPT = {"cookie", "pill", "aspirine", "band-aid", "chocolate", "chicken"};
    public static final String INPUT_REGEX = "([0-9]+) (.*) at ([0-9\\.]+)";
    public static final String IMPORTED_KEYWORD = "imported";



    public static void main (String[] args) {

        System.out.println("Invoice calculator");
        System.out.println("------------------");
        System.out.println("Input lines as: {n} [imported] {concept} at {price}");
        System.out.println("Remember to use only lowercase.");
        System.out.println("Ex: 5 imported bread at 2.30");
        System.out.println(StringUtils.EMPTY);
        System.out.println("Current tax exempt concepts: " + String.join(", ", EXEMPT));
        System.out.println(StringUtils.EMPTY);

        Scanner sc = new Scanner(System.in);

        List<Item> items = new ArrayList<>();

        do {
            String input = sc.nextLine();

            // Break condition
            if (StringUtils.isBlank(input)) {
                break;
            }

            Pattern p = Pattern.compile(INPUT_REGEX);
            Matcher m = p.matcher(input);

            if (!m.find()) {
                System.out.println("Format not recognized.");
                continue;
            }

            try {

                Item item = new Item();
                item.setCount(Integer.valueOf(m.group(1)));
                String name = m.group(2);
                item.setName(name);
                item.setUnitPrice(new BigDecimal(m.group(3)));

                if (name.contains(IMPORTED_KEYWORD)) {
                    item.setExported(true);
                }

                if (Arrays.stream(EXEMPT).anyMatch(name::contains)) {
                    item.setTaxesExempt(true);
                }

                items.add(item);

            } catch (NumberFormatException ex) {
                System.out.println("Incorrect number format.");
                continue;
            }

        } while(true);

        System.out.println("---End of input---");
        System.out.println("Results:");

        Calculator calculator = new Calculator();

        CalculatorResult result = calculator.calculate(items);

        for (Item item : result.getItems()) {
            System.out.println(item.getCount() + " " + item.getName() + ": " + item.getUnitPrice());
        }
        System.out.println("Sales Taxes: " + result.getSalesTaxes());
        System.out.println("Total: " + result.getTotal());

    }
}
