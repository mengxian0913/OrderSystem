package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order;
    ArrayList<String> menu;

    @BeforeEach
    void setup() {
        order = new Order();
        menu = new ArrayList<>();
    }

    @Test
    @DisplayName("測試案例 1: 測試每個分類都有一個項目")
    void testGetItemAmount() {
        // 準備測試數據
        menu.add("Pesto Pasta");
        menu.add("Beef Steak");
        menu.add("Chocolate Cake");
        menu.add("Caesar Salad");
        menu.add("Black Tea");
        menu.add("Fries");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{1, 1, 1, 1, 1,1}, result);
    }

    @Test
    @DisplayName("測試案例 2: 測試同一分類多個項目")
    void testGetItemAmountMultipleSameCategory() {
        menu.add("Pesto Pasta");
        menu.add("Garlic Pasta");
        menu.add("Creamy Pasta");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{3, 0, 0, 0, 0,0}, result);
    }

    @Test
    @DisplayName("測試案例 3: 測試空列表")
    void testGetItemAmountEmpty() {
        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 0, 0,0}, result);
    }

    @Test
    @DisplayName("測試案例 4: 測試混合情況")
    void testGetItemAmountMixed() {
        menu.add("Pesto Pasta");
        menu.add("Beef Steak");
        menu.add("Milk Tea");
        menu.add("Latte");
        menu.add("Chicken Nuggets");
        menu.add("Garlic Bread");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{1, 1, 0, 0, 2,2}, result);
    }

    @Test
    @DisplayName("測試案例 5: 測試第二類餐點")
    void testGetItemAmountSecondCategory() {
        menu.add("Lamb Chop");
        menu.add("Chicken Cutlet");
        menu.add("Pork Chop");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 3, 0, 0, 0,0}, result);
    }

    @Test
    @DisplayName("測試案例 6: 測試第三類餐點")
    void testGetItemAmountThirdCategory() {
        menu.add("Burnt Cream");
        menu.add("Mousse Cake");
        menu.add("Chocolate Cake");
        menu.add("Brownie");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 4, 0, 0,0}, result);
    }

    @Test
    @DisplayName("測試案例 7: 測試第四類餐點")
    void testGetItemAmountFourthCategory() {
        menu.add("Caesar Salad");
        menu.add("Greek Salad");
        menu.add("House Salad");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 3, 0,0}, result);
    }

    @Test
    @DisplayName("測試案例 8: 測試第五類餐點")
    void testGetItemAmountFifthCategory() {
        menu.add("Milk Tea");
        menu.add("Orange Juice");
        menu.add("Americano");
        menu.add("Cola");
        menu.add("Beer");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 0, 5,0}, result);
    }

    @Test
    @DisplayName("測試案例 9: 測試第六類餐點")
    void testGetItemAmountSixthCategory() {
        menu.add("Karaage");
        menu.add("Chicken Nuggets");
        menu.add("Garlic Bread");
        menu.add("Cheese Sticks");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 0, 0,4}, result);
    }

    @Test
    @DisplayName("測試案例 10: 測試無效菜單項目")
    void testInvalidMenuItem() {
        menu.add("Z");  // 無效的菜單項目
        assertThrows(Error.class, () -> order.orderMenu(menu));
    }

    @Test
    @DisplayName("測試案例 11: 測試空字串")
    void testEmptyString() {
        menu.add("");  // 空字串
        assertThrows(Error.class, () -> order.orderMenu(menu));
    }

    @Test
    @DisplayName("測試案例 12: 測試重複菜單")
    void testDuplicateItems() {
        menu.add("Pesto Pasta");
        menu.add("Pesto Pasta");
        menu.add("Pesto Pasta");
        menu.add("Pesto Pasta");
        menu.add("Chicken Cutlet");
        menu.add("Chicken Cutlet");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{4, 2, 0, 0, 0,0}, result);
    }

    @Test
    @DisplayName("測試案例 13: 測試大小寫混合")
    void testCaseSensitive() {
        menu.add("a");  // 小寫
        assertThrows(Error.class, () -> order.orderMenu(menu));
    }


    @Test
    @DisplayName("測試只有第三類和第四類餐點")
    void testMenuSetThirdAndFourth() {
        menu.add("Burnt Cream");  // 第三類
        menu.add("Greek Salad");  // 第四類


        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("測試第三類、第五類和第六類")
    void testMenuSetThirdFourthAndFirst() {
        menu.add("Chicken Nuggets");  // 第三類
        menu.add("Orange Juice");  // 第四類
        menu.add("Brownie");  // 第一類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("測試空菜單")
    void testMenuSetEmpty() {
        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(0, result);
    }


}