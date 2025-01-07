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
    @DisplayName("測試案例 0：字串長度拋錯")
    void testError() {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("AA");
        assertThrows(Error.class,  () -> order.orderMenu(menu));
    }

    @Test
    @DisplayName("測試案例 1: 測試每個分類都有一個項目")
    void testGetItemAmount() {
        // 準備測試數據
        menu.add("A");
        menu.add("E");
        menu.add("H");
        menu.add("L");
        menu.add("O");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, result);
    }

    @Test
    @DisplayName("測試案例 2: 測試同一分類多個項目")
    void testGetItemAmountMultipleSameCategory() {
        menu.add("A");
        menu.add("B");
        menu.add("C");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{3, 0, 0, 0, 0}, result);
    }

    @Test
    @DisplayName("測試案例 3: 測試空列表")
    void testGetItemAmountEmpty() {
        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, result);
    }

    @Test
    @DisplayName("測試案例 4: 測試混合情況")
    void testGetItemAmountMixed() {
        menu.add("A");
        menu.add("B");
        menu.add("E");
        menu.add("H");
        menu.add("H");
        menu.add("O");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{2, 1, 2, 0, 1}, result);
    }

    @Test
    @DisplayName("測試案例 5: 測試第二類餐點")
    void testGetItemAmountSecondCategory() {
        menu.add("E");
        menu.add("F");
        menu.add("G");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 3, 0, 0, 0}, result);
    }

    @Test
    @DisplayName("測試案例 6: 測試第三類餐點")
    void testGetItemAmountThirdCategory() {
        menu.add("H");
        menu.add("I");
        menu.add("J");
        menu.add("K");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 4, 0, 0}, result);
    }

    @Test
    @DisplayName("測試案例 7: 測試第四類餐點")
    void testGetItemAmountFourthCategory() {
        menu.add("L");
        menu.add("M");
        menu.add("N");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 3, 0}, result);
    }

    @Test
    @DisplayName("測試案例 8: 測試第五類餐點")
    void testGetItemAmountFifthCategory() {
        menu.add("O");
        menu.add("O");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{0, 0, 0, 0, 2}, result);
    }

    @Test
    @DisplayName("測試案例 9: 測試無效菜單項目")
    void testInvalidMenuItem() {
        menu.add("Z");  // 無效的菜單項目
        assertThrows(Error.class, () -> order.orderMenu(menu));
    }

    @Test
    @DisplayName("測試案例 10: 測試空字串")
    void testEmptyString() {
        menu.add("");  // 空字串
        assertThrows(Error.class, () -> order.orderMenu(menu));
    }

    @Test
    @DisplayName("測試案例 12: 測試重複菜單")
    void testDuplicateItems() {
        menu.add("A");
        menu.add("A");
        menu.add("B");
        menu.add("B");
        menu.add("C");
        menu.add("C");

        order.orderMenu(menu);
        int[] result = order.getItemAmount();
        assertArrayEquals(new int[]{6, 0, 0, 0, 0}, result);
    }

    @Test
    @DisplayName("測試案例 13: 測試大小寫混合")
    void testCaseSensitive() {
        menu.add("a");  // 小寫
        assertThrows(Error.class, () -> order.orderMenu(menu));
    }


    @Test
    @DisplayName("測試最大折扣組合 - 有第三類、第四類和第五類餐點")
    void testMenuSetMaxDiscount() {
        menu.add("H");  // 第三類
        menu.add("L");  // 第四類
        menu.add("O");  // 第五類
        menu.add("A");  // 第一類
        menu.add("E");  // 第二類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-175, result);
    }

    @Test
    @DisplayName("測試只有第三類和第四類餐點")
    void testMenuSetThirdAndFourth() {
        menu.add("H");  // 第三類
        menu.add("L");  // 第四類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-50, result);
    }

    @Test
    @DisplayName("測試第三類、第四類和第一類")
    void testMenuSetThirdFourthAndFirst() {
        menu.add("H");  // 第三類
        menu.add("L");  // 第四類
        menu.add("A");  // 第一類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-100, result);
    }

    @Test
    @DisplayName("測試第三類、第四類、第一類和第二類")
    void testMenuSetWithoutFifth() {
        menu.add("H");  // 第三類
        menu.add("L");  // 第四類
        menu.add("A");  // 第一類
        menu.add("E");  // 第二類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-150, result);
    }

    @Test
    @DisplayName("測試第三類和第五類的基本組合")
    void testMenuSetThirdAndFifth() {
        menu.add("H");  // 第三類
        menu.add("O");  // 第五類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-25, result);
    }

    @Test
    @DisplayName("測試第三類、第五類和第一類")
    void testMenuSetThirdFifthAndFirst() {
        menu.add("H");  // 第三類
        menu.add("O");  // 第五類
        menu.add("A");  // 第一類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-50, result);
    }

    @Test
    @DisplayName("測試第三類、第五類和第二類")
    void testMenuSetThirdFifthAndSecond() {
        menu.add("H");  // 第三類
        menu.add("O");  // 第五類
        menu.add("E");  // 第二類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-50, result);
    }

    @Test
    @DisplayName("測試多組合的情況")
    void testMenuSetMultipleCombinations() {
        menu.add("H");  // 第三類
        menu.add("H");  // 第三類
        menu.add("L");  // 第四類
        menu.add("L");  // 第四類
        menu.add("A");  // 第一類
        menu.add("E");  // 第二類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-300, result);  // 兩組 -150
    }

    @Test
    @DisplayName("測試沒有可用組合")
    void testMenuSetNoValidCombination() {
        menu.add("A");  // 第一類
        menu.add("E");  // 第二類
        menu.add("L");  // 第四類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("測試複雜組合 - 混合第四類和第五類")
    void testMenuSetMixedCombinations() {
        menu.add("H");  // 第三類
        menu.add("H");  // 第三類
        menu.add("L");  // 第四類
        menu.add("O");  // 第五類
        menu.add("A");  // 第一類
        menu.add("E");  // 第二類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-175, result);  // -175 + -150
    }

    @Test
    @DisplayName("測試空菜單")
    void testMenuSetEmpty() {
        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(0, result);
    }

    @Test
    @DisplayName("測試最大可能組合數量")
    void testMenuSetMaximumCombinations() {
        // 添加多個項目以測試多重組合
        menu.add("H"); menu.add("H"); menu.add("H");  // 第三類
        menu.add("L"); menu.add("L"); menu.add("L");  // 第四類
        menu.add("O"); menu.add("O"); menu.add("O");  // 第五類
        menu.add("A"); menu.add("A"); menu.add("A");  // 第一類
        menu.add("E"); menu.add("E"); menu.add("E");  // 第二類

        order.orderMenu(menu);
        int result = order.menuSet();
        assertEquals(-525, result);  // 三組 -175
    }
}