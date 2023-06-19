package org.rmmcosta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesProcessorTest {

    @Test
    void testMatchingCategory() {
        CategoriesProcessor categoriesProcessor = new CategoriesProcessor("src\\test\\resources\\TestCategories.csv");
        assertEquals("Food", categoriesProcessor.getCategory("Deliveroo"));
        assertEquals("Income", categoriesProcessor.getCategory("Salary"));
        /*Deliveroo,Food
        Salary,Income*/
    }
}