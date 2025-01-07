package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class MembershipTest {

    Membership membership;

    @BeforeEach
    void setup() {
        membership = new Membership("0987654321", "TEST");
    }


    @Test
    @DisplayName("優惠券：都還沒換過且累積價格 < 5000")
    void consumptionTest() {
        membership.addConsumption(1000);
        assertAll("確定 10off 15off 數量",
            () -> assertEquals(0, membership.getCoupon10off()),
            () -> assertEquals(0, membership.getCoupon15off())
        );
    }

    @Test
    @DisplayName("優惠券：換過 10 off 且 累計價格 < 10_000")
    void consumptionTest2() {
        membership.addConsumption(5000);
        membership.addConsumption(1000);
        assertAll("確定 10off 15off 數量",
            () -> assertEquals(1, membership.getCoupon10off()),
            () -> assertEquals(0, membership.getCoupon15off())
        );
    }

    @Test
    @DisplayName("優惠券：換過 10 off 且 累計價格 >= 10_000")
    void consumptionTest3() {
        membership.addConsumption(9999);
        membership.addConsumption(1);
        assertAll("確定 10off 15off 數量",
                () -> assertEquals(1, membership.getCoupon10off()),
                () -> assertEquals(1, membership.getCoupon15off())
        );
    }

    @Test
    @DisplayName("優惠券：都沒換過且價格 >= 5000")
    void consumptionTest4() {
        membership.addConsumption(5000);
        assertAll("確定 10off 15off 數量",
                () -> assertEquals(1, membership.getCoupon10off()),
                () -> assertEquals(0, membership.getCoupon15off())
        );
    }

    @Test
    @DisplayName("優惠券：換過 15off 確認累計價格重置")
    void consumptionTest5() {
        membership.addConsumption(9999);
        membership.addConsumption(1);
        assertEquals(membership.getAccumulatedPrice(), 0);
    }

    @Test
    @DisplayName("測試 setinfo")
    void setInfoTest() {
        membership.setInfo("0000000000", "MODIFY");
        assertAll("確認電話號碼和名字",
            () -> assertEquals("0000000000", membership.getPhoneNumber()),
            () -> assertEquals("MODIFY", membership.getName())
        );
    }


    @Test
    @DisplayName("測試 subCoupon15off")
    void subCoupon15offTest() {
        membership.addConsumption(9999);
        membership.addConsumption(1);
        membership.subCoupon15off();
        assertEquals(0, membership.getCoupon15off());
    }

    @Test
    @DisplayName("測試 subCoupon10off")
    void subCoupon10offTest() {
        membership.addConsumption(5000);
        membership.subCoupon10off();
        assertEquals(0, membership.getCoupon10off());
    }
}