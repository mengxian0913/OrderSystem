package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class OrderSystemTest {
    OrderSystem system;

    @BeforeEach
    void setup() {
        system = new OrderSystem();
    }

    @Test
    @DisplayName("Membership Test")
    void membershipTest() {
        assertAll("會員相關功能測試",
                () -> {
                    system.addMembership("0987654321", "Test1");
                    assertEquals("Test1", system.findMembership("0987654321", "Test1").getName());
                },
                () -> {
                    Membership member = new Membership("0912345678", "Test2");
                    system.addMembership(member);
                    assertEquals("Test2", system.findMembership("0912345678", "Test2").getName());
                },
                () -> {
                    system.modifyMemberInfo("0987654321", "Test1",
                            new String[]{"0987654321", "NewTest1"});
                    assertEquals("NewTest1", system.findMembership("0987654321", "NewTest1").getName());
                }
        );
    }

    @Test
    @DisplayName("findMembership Test")
    void findMembershipTest() {
        Membership member = new Membership("0000000000", "TEST");
        Membership UNFind = new Membership();
        system.addMembership(member);

        assertAll("Find membership",
            () -> {
                assertEquals(member.getPhoneNumber(), system.findMembership("0000000000", "TEST").getPhoneNumber());
            },
            () -> {
                assertEquals(UNFind.getPhoneNumber(), system.findMembership("0000000001", "TEST").getPhoneNumber());
            },
            () -> {
                assertEquals(UNFind.getPhoneNumber(), system.findMembership("0000000000", "TESS").getPhoneNumber());
            }
        );
    }


    @Test
    @DisplayName("modifyMemberInfo Test")
    void modifyMemberInfoTest() {
        Membership member = new Membership("0000000000", "TEST");
        system.addMembership(member);

        assertAll("modifyMemberInfo",
                () -> {
                    system.modifyMemberInfo("0000000000", "TEST", new String[]{"0000000001", "TEST"});
                },
                () -> {
                    system.modifyMemberInfo("0000000001", "TEST", new String[]{"0000000001", "TEST"});
                },
                () -> {
                    system.modifyMemberInfo("0000000000", "TESS", new String[]{"0000000001", "TEST"});
                },
                () -> {
                    system.modifyMemberInfo("0000000001", "TESS", new String[]{"0000000001", "TEST"});
                }
        );
    }

    @Test
    @DisplayName("Booking Test")
    void bookingTest() {
        assertAll("訂位功能測試",
                () -> {
                    system.booking("", "", 1);  // 不能訂位了
                    assertThrows(AssertionError.class, () -> system.booking("", "", 1));
                },
                () -> {
                    system.addMembership("0987654321", "Test");
                    system.booking("0987654321", "Test", 2);  // 會員訂位
                },
                () -> {
                    system.booking("0101010101", "EMPTY", 3);  // 非會員訂位
                }

        );
    }

    @Test
    @DisplayName("Order and Checkout Test")
    void orderAndCheckoutTest() {
        assertAll("點餐與結帳功能測試",
                () -> {
                    system.booking("", "", 1);
                    system.order("Black Tea", 1);
                    int price = Menu.BLACK_TEA.getDishPrice();
                    assertEquals((int)Math.round(price * 1.1),
                            system.checkout(1, "2024-1-8 12", false, false, false, false));
                },
                () -> {
                    system.addMembership("0987654321", "Test");
                    system.booking("0987654321", "Test", 2);
                    system.order("Black Tea", 2);
                    int price = Menu.BLACK_TEA.getDishPrice();
                    assertEquals((int)Math.round(price * 1.1),
                            system.checkout(2, "2024-1-8 12", false, false, false, false));
                }
        );
    }
}
