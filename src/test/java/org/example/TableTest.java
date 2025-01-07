package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    Table table;

    @Test
    @DisplayName("test Member and Discount")
    void memberAndDiscountTest() {
        assertAll("測試會員和折價券",
                () -> {
                    // 是會員且有 15off 折價券
                    Membership member = new Membership("0987654321", "TEST");
                    member.addConsumption(9999);
                    member.addConsumption(1);
                    table = new Table(member);
                    assertEquals(0.85, table.useCouponDiscount());
                },
                () -> {
                    // 是會員但只有 10off 折價券
                    Membership member = new Membership("0987654321", "TEST");
                    member.addConsumption(5000);
                    table = new Table(member);
                    assertEquals(0.9, table.useCouponDiscount());
                },
                () -> {
                    // 是會員但都沒有折價券
                    Membership member = new Membership("0987654321", "TEST");
                    member.addConsumption(1);
                    table = new Table(member);
                    assertEquals(1, table.useCouponDiscount());
                },
                () -> {
                    // 不是會員
                    table = new Table();
                    assertEquals(1, table.useCouponDiscount());
                }
        );
    }

    @Test
    @DisplayName("測試當前時間")
    void isNightOrWeekendTest() {
        table = new Table();
        assertAll("測試時間加價條件",
                () -> assertTrue(table.isNightOrWeekend("2024-1-6 12")),  // 週六中午
                () -> assertTrue(table.isNightOrWeekend("2024-1-7 12")),  // 週日中午
                () -> assertTrue(table.isNightOrWeekend("2024-1-8 18")),  // 平日晚上6點
                () -> assertTrue(table.isNightOrWeekend("2024-1-8 19")),  // 平日晚上7點
                () -> assertTrue(table.isNightOrWeekend("2024-1-6 19")),  // 週六晚上 (週末+晚上)
                () -> assertFalse(table.isNightOrWeekend("2024-1-8 12")), // 平日中午
                () -> assertFalse(table.isNightOrWeekend("2024-1-8 17"))  // 平日下午5點
        );
    }

    @Test
    @DisplayName("Coupon Test")
    void couponTest() {
        Table normalTable = new Table();
        Table memberTable = new Table(new Membership("0987654321", "Test"));

        normalTable.orderFood("Black Tea");
        normalTable.submitOrder();
        memberTable.orderFood("Black Tea");
        memberTable.submitOrder();

        int basePrice = Menu.BLACK_TEA.getDishPrice();

        assertAll("測試優惠與服務費計算",
                // 非會員
                () -> assertEquals((int)Math.round(basePrice * 0.7),
                        normalTable.coupon("2024-1-8 12", false, true, false, false)),
                () -> assertEquals((int)Math.round(basePrice * 0.8 * 1.1),
                        normalTable.coupon("2024-1-8 12", true, false, false, false)),
                () -> assertEquals((int)Math.round(basePrice * 0.85 * 1.1),
                        normalTable.coupon("2024-1-8 12", false, false, true, false)),
                () -> assertEquals((int)Math.round(basePrice * 1.1),
                        normalTable.coupon("2024-1-8 12", false, false, false, false)),

                // 會員
                () -> assertEquals((int)Math.round(basePrice * 0.7),
                        memberTable.coupon("2024-1-8 12", false, true, false, false)),
                () -> assertEquals((int)Math.round(basePrice * 0.8 * 1.1),
                        memberTable.coupon("2024-1-8 12", true, false, false, false)),
                () -> assertEquals((int)Math.round(basePrice * 0.85 * 1.1),
                        memberTable.coupon("2024-1-8 12", false, false, true, false)),

                // 會員折價券
                () -> {
                    Membership member = new Membership("0987654321", "Test");
                    member.addCoupon15off();
                    Table couponTable = new Table(member);
                    couponTable.orderFood("Black Tea");
                    couponTable.submitOrder();
                    assertEquals((int)Math.round(basePrice * 0.85 * 1.1),
                            couponTable.coupon("2024-1-8 12", false, false, false, true));
                },

                // 時段測試
                () -> assertEquals((int)Math.round(basePrice * 1.1),
                        normalTable.coupon("2024-1-8 12", false, false, false, false)),
                () -> assertEquals((int)Math.round(basePrice * 1.15),
                        normalTable.coupon("2024-1-8 19", false, false, false, false))
        );
    }

    @Test
    @DisplayName("Booking Status Test")
    void bookingStatusTest() {
        table = new Table();
        table.bookingStatus();
        assertEquals(true, table.tableStatus());
    }

    @Test
    @DisplayName("Booking Status Test")
    void restoreStatusTest() {
        table = new Table();
        table.bookingStatus();
        table.restoreStatus();
        assertEquals(false, table.tableStatus());
    }
}
