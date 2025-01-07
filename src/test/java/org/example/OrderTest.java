package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;

    @BeforeEach
    void setup() {
        order = new Order();
    }

    @Test
    @DisplayName("Order Menu Test")
    void orderMenuTest() {
        assertAll("測試點餐功能",
            () -> {
                ArrayList<String> invalidMenu = new ArrayList<>(Arrays.asList("Invalid Dish"));
                assertThrows(IllegalArgumentException.class, () -> order.orderMenu(invalidMenu));
            },
            () -> {
                ArrayList<String> emptyMenu = new ArrayList<>();
                order.orderMenu(emptyMenu);
                assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, order.getItemAmount());
            }
        );
    }


    @Test
    @DisplayName("Get Item Amount Test")
    void getItemAmountTest() {
        assertAll("測試餐點數量計算",
                () -> {
                    ArrayList<String> allPasta = new ArrayList<>(Arrays.asList(
                            "Pesto Pasta", "Garlic Pasta", "Creamy Pasta", "Tomato Pasta"
                    ));
                    order.orderMenu(allPasta);
                    assertEquals(4, order.getItemAmount()[0]);
                },
                () -> {
                    order = new Order();
                    ArrayList<String> allTypes = new ArrayList<>(Arrays.asList(
                            "Pesto Pasta", "Beef Steak", "Chocolate Cake",
                            "Greek Salad", "Black Tea", "Fries"
                    ));
                    order.orderMenu(allTypes);
                    assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1}, order.getItemAmount());
                }
        );
    }

    @Test
    @DisplayName("Steak Amount Test")
    void getSteakAmountTest() {
        assertAll("測試主餐數量",
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Beef Steak")));
                    assertEquals(1, order.getItemAmount()[1]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Pork Chop")));
                    assertEquals(1, order.getItemAmount()[1]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Chicken Cutlet")));
                    assertEquals(1, order.getItemAmount()[1]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Lamb Chop")));
                    assertEquals(1, order.getItemAmount()[1]);
                }
        );
    }

    @Test
    @DisplayName("Dessert Amount Test")
    void getDessertAmountTest() {
        assertAll("測試甜點數量",
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Chocolate Cake")));
                    assertEquals(1, order.getItemAmount()[2]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Burnt Cream")));
                    assertEquals(1, order.getItemAmount()[2]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Mousse Cake")));
                    assertEquals(1, order.getItemAmount()[2]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Brownie")));
                    assertEquals(1, order.getItemAmount()[2]);
                }
        );
    }

    @Test
    @DisplayName("Salad Amount Test")
    void getSaladAmountTest() {
        assertAll("測試沙拉數量",
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Greek Salad")));
                    assertEquals(1, order.getItemAmount()[3]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Caesar Salad")));
                    assertEquals(1, order.getItemAmount()[3]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("House Salad")));
                    assertEquals(1, order.getItemAmount()[3]);
                }
        );
    }


    @Test
    @DisplayName("Drinks Amount Test")
    void getDrinkAmountTest() {
        assertAll("測試飲料數量",
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Black Tea")));
                    assertEquals(1, order.getItemAmount()[4]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Milk Tea")));
                    assertEquals(1, order.getItemAmount()[4]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Orange Juice")));
                    assertEquals(1, order.getItemAmount()[4]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Latte")));
                    assertEquals(1, order.getItemAmount()[4]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Americano")));
                    assertEquals(1, order.getItemAmount()[4]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Cola")));
                    assertEquals(1, order.getItemAmount()[4]);
                },
                () -> {
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Beer")));
                    assertEquals(1, order.getItemAmount()[4]);
                }
        );
    }

    @Test
    @DisplayName("Appetizer Amount Test")
    void getAppetizerAmountTest() {
        assertAll("測試開胃菜數量",
            () -> {
                order = new Order();
                order.orderMenu(new ArrayList<>(List.of("Fries")));
                assertEquals(1, order.getItemAmount()[5]);
            },
            () -> {
                order = new Order();
                order.orderMenu(new ArrayList<>(List.of("Karaage")));
                assertEquals(1, order.getItemAmount()[5]);
            },
            () -> {
                order = new Order();
                order.orderMenu(new ArrayList<>(List.of("Chicken Nuggets")));
                assertEquals(1, order.getItemAmount()[5]);
            },
            () -> {
                order = new Order();
                order.orderMenu(new ArrayList<>(List.of("Garlic Bread")));
                assertEquals(1, order.getItemAmount()[5]);
            },
            () -> {
                order = new Order();
                order.orderMenu(new ArrayList<>(List.of("Cheese Sticks")));
                assertEquals(1, order.getItemAmount()[5]);
            }
        );
    }



    @Test
    @DisplayName("Menu Set Test")
    void menuSetTest() {
        assertAll("測試 menuSet 條件分支",
                () -> {
                    // itemCount[0] > 0, itemCount[1] = 0
                    order.orderMenu(new ArrayList<>(List.of("Pesto Pasta")));
                    order.orderConsumption();
                    int expected = Menu.PESTO_PASTA.getDishPrice();
                    assertEquals(expected, order.getConsumption());
                },
                () -> {
                    // itemCount[0] = 0, itemCount[1] > 0
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Beef Steak")));
                    order.orderConsumption();
                    assertEquals(Menu.BEEF_STEAK.getDishPrice(), order.getConsumption());
                },
                () -> {
                    // itemCount[0] = 0, itemCount[1] = 0
                    order = new Order();
                    order.orderMenu(new ArrayList<>(List.of("Black Tea")));
                    order.orderConsumption();
                    assertEquals(Menu.BLACK_TEA.getDishPrice(), order.getConsumption());
                },
                () -> {
                    // itemCount[0] > 0, itemCount[1] > 0
                    order = new Order();
                    order.orderMenu(new ArrayList<>(Arrays.asList("Pesto Pasta", "Beef Steak")));
                    order.orderConsumption();
                    int expected = Menu.PESTO_PASTA.getDishPrice() + Menu.BEEF_STEAK.getDishPrice();
                    assertEquals(expected, order.getConsumption());
                }
        );
    }
}