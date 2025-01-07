package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SystemTest {
    OrderSystem order ;

    @BeforeEach
    public void beforeTest(){
        order=new OrderSystem();
    }

    @Test
    public void testAddMembership() {
        Membership member = new Membership("0912345789","test");
        order.addMembership(member);
        assertEquals(member,order.findMembership("0912345789","test"));
        member.setInfo("0987654321","TEST");
        assertEquals(member,order.findMembership("0987654321","TEST"));
        member.addConsumption(2500);
        assertEquals(0,member.getCoupon10off());
        member.addConsumption(2501);
        assertEquals(1,member.getCoupon10off());
        member.addConsumption(2500);
        member.addConsumption(2501);
        assertEquals(1,member.getCoupon15off());
        member.addConsumption(9998);
        assertEquals(2,member.getCoupon10off());
        assertEquals(10000,member.getAccumulatedPrice());
    }

    @Test
    public void testSetA() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);

        settest=new OrderSystem();
        settest.booking("","",3);
//        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
//        assertEquals(0,total);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        System.out.println(total);
        assertEquals(474,total);
    }

    @Test
    public void testSetB() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);
    }

    @Test
    public void testSetC() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);
    }

    @Test
    public void testSetD() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);
    }

    @Test
    public void testSetE() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);
    }

    @Test
    public void testSetF() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);
    }

    @Test
    public void testOrder()  {
        order.booking("","",1);
        order.order("Chocolate Cake",1);
        int total=order.checkout(1,"2024-12-28 10",true,false,false,false);
        assertEquals(460,total);
    }

    @Test
    public void testAccumulatedPrice()  {
        order.addMembership("0968344000","Alex");
        order.booking("0968344000","Alex",1);
        order.order("Chocolate Cake",1);
        order.checkout(1,"2024-12-28 10",true,false,false,false);
        order.booking("0968344000","Alex",1);
        order.order("Chocolate Cake",1);
        order.checkout(1,"2024-12-28 12",true,false,false,false);
        assertEquals(920,order.findMembership("0968344000","Alex").getAccumulatedPrice());
    }
}