package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        String []temp = {"0912345678","Test"};
        order.modifyMemberInfo("0987654321","TEST",temp);
        order.modifyMemberInfo("0987654322","TEST",temp);
        order.modifyMemberInfo("0912345678","T",temp);

        order.findMembership("0987654322","TEST");
        order.findMembership("0912345678","T");

        Membership m = new Membership("","test");
        order.booking("0912345678","TEST",1);
        order.booking("","test",2);
        assertThrows(AssertionError.class, ()->order.booking("0912345679","TEST",1));
    }

    @Test
    public void testSetA() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Beef Steak",2);
        settest.order("Orange Juice",2);
        int total = settest.checkout(2,"2024-12-26 10",false,true,false,false);
        assertEquals(441,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",true,false,false,false);
        assertEquals(379,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Pork Chop",3);
        settest.order("Latte",3);
        settest.order("Garlic Pasta",3);
        total = settest.checkout(3,"2024-12-29 10",false,false,true,false);
        assertEquals(1007,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Creamy Pasta",3);
        settest.order("Tomato Pasta",3);
        settest.order("Americano",3);
        total = settest.checkout(3,"2024-12-26 22",false,false,false,true);
        assertEquals(1012,total);

    }

    @Test
    public void coupon() {
        Membership member = new Membership("0912345789","test");
        OrderSystem settest=new OrderSystem();
        settest.addMembership(member);
        settest.booking("0912345789","test",3);
        settest.order("Creamy Pasta",3);
        settest.order("Tomato Pasta",3);
        settest.order("Americano",3);
        int total = settest.checkout(3,"2024-12-26 22",false,false,false,true);
        assertEquals(1012,total);

        member = new Membership("0912345789","test");
        settest=new OrderSystem();
        member.addCoupon10off();
        settest.addMembership(member);
        settest.booking("0912345789","test",3);
        settest.order("Creamy Pasta",3);
        settest.order("Tomato Pasta",3);
        settest.order("Americano",3);
        total = settest.checkout(3,"2024-12-26 22",false,false,false,true);
        assertEquals(911,total);

        Membership member1 = new Membership("0912345781","Test");
        OrderSystem settest1 = new OrderSystem();
        member1.addCoupon15off();
        settest1.addMembership(member1);
        settest1.booking("0912345781","Test",4);
        settest1.order("Creamy Pasta",4);
        settest1.order("Tomato Pasta",4);
        settest1.order("Americano",4);
        total = settest1.checkout(4,"2024-12-26 22",false,false,false,true);
        assertEquals(861,total);
    }
    @Test
    public void testSetB() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Chicken Cutlet",2);
        settest.order("Fries",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(671,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Pesto Pasta",3);
        settest.order("Fries",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(561,total);
    }

    @Test
    public void testSetC() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Caesar Salad",2);
        settest.order("Lamb Chop",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(881,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Caesar Salad",3);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(771,total);

        settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Caesar Salad",2);
        settest.order("Caesar Salad",2);
        settest.order("Beef Steak",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(1761,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Caesar Salad",3);
        settest.order("Caesar Salad",3);
        settest.order("Pesto Pasta",3);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(1541,total);
    }

    @Test
    public void testSetD() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Fries",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(693,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Fries",3);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(583,total);
    }

    @Test
    public void testSetE() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Chocolate Cake",2);
        settest.order("Beef Steak",2);
        settest.order("Chocolate Cake",2);
        settest.order("Beef Steak",2);
        settest.order("Black Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(2145,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Burnt Cream",3);
        settest.order("Pesto Pasta",3);
        settest.order("Chocolate Cake",3);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(1706,total);
    }

    @Test
    public void testSetF() {
        OrderSystem settest=new OrderSystem();
        settest.booking("","",2);
        settest.order("Caesar Salad",2);
        settest.order("Garlic Bread",2);
        settest.order("Chocolate Cake",2);
        settest.order("Beef Steak",2);
        settest.order("Caesar Salad",2);
        settest.order("Cheese Sticks",2);
        settest.order("Chocolate Cake",2);
        settest.order("Beef Steak",2);
        settest.order("Milk Tea",2);
        int total = settest.checkout(2,"2024-12-26 10",false,false,false,false);
        assertEquals(2949,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Caesar Salad",3);
        settest.order("Karaage",3);
        settest.order("Mousse Cake",3);
        settest.order("House Salad",3);
        settest.order("Chocolate Cake",3);
        settest.order("Pesto Pasta",3);
        settest.order("Cola",3);
        settest.order("Beer",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(1981,total);

        settest=new OrderSystem();
        settest.booking("","",3);
        settest.order("Greek Salad",3);
        settest.order("Chicken Nuggets",3);
        settest.order("Brownie",3);
        settest.order("Caesar Salad",3);
        settest.order("Chocolate Cake",3);
        settest.order("Pesto Pasta",3);
        settest.order("Pesto Pasta",3);
        settest.order("Black Tea",3);
        settest.order("Black Tea",3);
        total = settest.checkout(3,"2024-12-26 10",false,false,false,false);
        assertEquals(2255,total);
    }

    @Test
    public void testOrder()  {
        order.booking("","",1);
        order.order("Chocolate Cake",1);
        int total=order.checkout(1,"2024-12-28 10",true,false,false,false);
        assertEquals(460,total);
        order.order("ABC",1);
        assertThrows(AssertionError.class, ()->order.checkout(1,"2024-12-28 10",true,false,false,false));
    }

    @Test
    public void testAccumulatedPrice()  {
        order.addMembership("0968344000","Alex");
        order.booking("0968344000","Alex",1);
        order.order("Chocolate Cake",1);
        order.checkout(1,"2024-12-28 10",true,false,false,false);
        order.booking("0968344000","Alex",1);
        order.order("Chocolate Cake",1);
        order.checkout(1,"2024-12-25 12",true,false,false,false);
        assertEquals(901,order.findMembership("0968344000","Alex").getAccumulatedPrice());
    }
}
