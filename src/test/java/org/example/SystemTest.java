package org.example;

import org.junit.Test;

import static org.junit.Assert.*;


public class SystemTest {

    @Test
    public void addMembership() {
        Order os = new Order();
        String []name = new String[]{"A","A","C","D"};
        os.orderMenu(name);
        os.orderConsumption();
        assertEquals(1550,os.getConsumption());
    }

}