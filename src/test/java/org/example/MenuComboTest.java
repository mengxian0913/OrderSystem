package org.example;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuComboTest {
    MenuCombo menuCombo;

    @Test
    @DisplayName("combo: A")
    void comboATest() {
        assertAll("測試 1st while 裡的分支",
            () -> {
                int[] amount = {2, 0, 0, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboA();
                assertEquals(40, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 0, 0, 0, 2, 0}; // amount[0]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboA();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {2, 0, 0, 0, 0, 0}; // amount[4]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboA();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 2, 0, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboA();
                assertEquals(40, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 0, 0, 0, 2, 0}; // amount[0]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboA();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 2, 0, 0, 0, 0}; // amount[4]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboA();
                assertEquals(0, menuCombo.getAdjustPrice());
            }
        );
    }

    @Test
    @DisplayName("combo: B")
    void comboBTest() {
        assertAll("測試 while 裡的分支",
            () -> {
                int[] amount = {2, 0, 0, 0, 0, 2};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboB();
                assertEquals(80, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 0, 0, 0, 0, 2}; // amount[0]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboB();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {2, 0, 0, 0, 0, 0}; // amount[4]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboB();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 2, 0, 0, 0, 2};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboB();
                assertEquals(80, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 0, 0, 0, 0, 2}; // amount[1]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboB();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 2, 0, 0, 0, 0}; // amount[5]>0 為 false
                menuCombo = new MenuCombo(amount);
                menuCombo.comboB();
                assertEquals(0, menuCombo.getAdjustPrice());
            }
        );
    }



    @Test
    @DisplayName("combo C")
    void comboCTest() {
        assertAll("測試所有分支條件",
            // 第一個 while 的測試
            () -> {
                // true && true && true
                int[] amount = {2, 0, 0, 2, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(100, menuCombo.getAdjustPrice());
            },
            () -> {
                // false && true && true
                int[] amount = {0, 0, 0, 2, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                // true && false && true
                int[] amount = {2, 0, 0, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                // true && true && false
                int[] amount = {2, 0, 0, 2, 0, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            // 第二個 while 的測試
            () -> {
                // true && true && true
                int[] amount = {0, 2, 0, 2, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(100, menuCombo.getAdjustPrice());
            },
            () -> {
                // false && true && true
                int[] amount = {0, 0, 0, 2, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                // true && false && true
                int[] amount = {0, 2, 0, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                // true && true && false
                int[] amount = {0, 2, 0, 2, 0, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboC();
                assertEquals(0, menuCombo.getAdjustPrice());
            }
        );
    }

    @Test
    @DisplayName("combo D")
    void comboDTest() {
        assertAll("測試分支條件",
                // 第一個 while
                () -> {
                    int[] amount = {2, 0, 0, 0, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(140, menuCombo.getAdjustPrice());
                },
                () -> {
                    int[] amount = {0, 0, 0, 0, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    int[] amount = {2, 0, 0, 0, 0, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    int[] amount = {2, 0, 0, 0, 2, 0};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                // 第二個 while
                () -> {
                    int[] amount = {0, 2, 0, 0, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(140, menuCombo.getAdjustPrice());
                },
                () -> {
                    int[] amount = {0, 0, 0, 0, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    int[] amount = {0, 2, 0, 0, 0, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    int[] amount = {0, 2, 0, 0, 2, 0};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboD();
                    assertEquals(0, menuCombo.getAdjustPrice());
                }
        );
    }

    @Test
    @DisplayName("combo E")
    void comboETest() {
        assertAll("測試分支條件",
            // 第一個 while
            () -> {
                int[] amount = {2, 0, 2, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(200, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 0, 2, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {2, 0, 0, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {2, 0, 2, 0, 0, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            // 第二個 while
            () -> {
                int[] amount = {0, 2, 2, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(200, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 0, 2, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 2, 0, 0, 2, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(0, menuCombo.getAdjustPrice());
            },
            () -> {
                int[] amount = {0, 2, 2, 0, 0, 0};
                menuCombo = new MenuCombo(amount);
                menuCombo.comboE();
                assertEquals(0, menuCombo.getAdjustPrice());
            }
        );
    }

    @Test
    @DisplayName("combo F")
    void comboFTest() {
        assertAll("測試分支條件",
                () -> {
                    // amount[0]>0 && true && true && true && true
                    int[] amount = {2, 0, 2, 2, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(500, menuCombo.getAdjustPrice());
                },
                () -> {
                    // amount[1]>0 && true && true && true && true
                    int[] amount = {0, 2, 2, 2, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(500, menuCombo.getAdjustPrice());
                },
                () -> {
                    // false && false && true && true && true && true
                    int[] amount = {0, 0, 2, 2, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    // true && false && true && true && true
                    int[] amount = {2, 0, 0, 2, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    // true && true && false && true && true
                    int[] amount = {2, 0, 2, 0, 2, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    // true && true && true && false && true
                    int[] amount = {2, 0, 2, 2, 0, 2};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(0, menuCombo.getAdjustPrice());
                },
                () -> {
                    // true && true && true && true && false
                    int[] amount = {2, 0, 2, 2, 2, 0};
                    menuCombo = new MenuCombo(amount);
                    menuCombo.comboF();
                    assertEquals(0, menuCombo.getAdjustPrice());
                }
        );
    }

}