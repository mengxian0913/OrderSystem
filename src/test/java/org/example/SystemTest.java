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
        assertEquals(member,order.isMembership("0912345789","test"));
    }

    @Test
    public void testOrder()  {
        order.booking("","",1);
        order.order("A",1);
        int total=order.checkout(1,"2024-12-28 10",true,false,false,false);
        assertEquals(460,total);
    }

    @Test
    public void testAccumulatedPrice()  {
        order.addMembership("0968344000","Alex");
        order.booking("0968344000","Alex",1);
        order.order("A",1);
        order.checkout(1,"2024-12-28 10",true,false,false,false);
        order.booking("0968344000","Alex",1);
        order.order("A",1);
        order.checkout(1,"2024-12-28 12",true,false,false,false);
        assertEquals(920,order.isMembership("0968344000","Alex").getAccumulatedPrice());
    }
}