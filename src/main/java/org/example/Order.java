package org.example;

import java.util.ArrayList;

public class Order {
    private int totalConsumption;
    private final ArrayList<Menu> orderList;

    Order() {
        totalConsumption = 0;
        orderList = new ArrayList<>();
    }

    public void orderMenu(ArrayList<String> menu)  throws Error {
        for (String menuName : menu) {
            if (menuName.length() != 1) {
                throw new AssertionError("菜單代號有誤");
            }
            switch (menuName) {
                case "A":
                    orderList.add(Menu.A);
                    break;
                case "B":
                    orderList.add(Menu.B);
                    break;
                case "C":
                    orderList.add(Menu.C);
                    break;
                case "D":
                    orderList.add(Menu.D);
                    break;
                case "E":
                    orderList.add(Menu.E);
                    break;
                case "F":
                    orderList.add(Menu.F);
                    break;
                case "G":
                    orderList.add(Menu.G);
                    break;
                case "H":
                    orderList.add(Menu.H);
                    break;
                case "I":
                    orderList.add(Menu.I);
                    break;
                case "J":
                    orderList.add(Menu.J);
                    break;
                case "K":
                    orderList.add(Menu.K);
                    break;
                case "L":
                    orderList.add(Menu.L);
                    break;
                case "M":
                    orderList.add(Menu.M);
                    break;
                case "N":
                    orderList.add(Menu.N);
                    break;
                case "O":
                    orderList.add(Menu.O);
                    break;
                default:
                    throw new AssertionError("菜單代號有誤");
            }
        }
    }

    public void orderConsumption() {
        for (Menu item : orderList) {
            this.totalConsumption += item.getPrice();
        }
        totalConsumption = totalConsumption - menuSet();
    }

    public int[] getItemAmount() {
        int[] temp = new int[5];
        for (Menu item : orderList) {
            if (item.equals(Menu.A) || item.equals(Menu.B) || item.equals(Menu.C) || item.equals(Menu.D)) {
                temp[0]++;
            }
            if (item.equals(Menu.E) || item.equals(Menu.F) || item.equals(Menu.G)) {
                temp[1]++;
            }
            if (item.equals(Menu.H) || item.equals(Menu.I) || item.equals(Menu.J) || item.equals(Menu.K)) {
                temp[2]++;
            }
            if (item.equals(Menu.L) || item.equals(Menu.M) || item.equals(Menu.N)) {
                temp[3]++;
            }
            if (item.equals(Menu.O)) {
                temp[4]++;
            }
        }
        return temp;
    }

    public int menuSet() {
        int[] itemCount = getItemAmount();
        int adjust = 0;
        while (itemCount[2] > 0 && (itemCount[3] > 0 || itemCount[4] > 0)) {
            if (itemCount[3] > 0) {
                if (itemCount[1] > 0) {
                    if (itemCount[0] > 0) {
                        if (itemCount[4] > 0) {
                            adjust -= 175;
                            itemCount[4]--;
                            itemCount[3]--;
                            itemCount[2]--;
                        } else {
                            adjust -= 150;
                            itemCount[3]--;
                            itemCount[2]--;
                        }

                    } else {
                        adjust -= 100;
                        itemCount[3]--;
                        itemCount[2]--;
                    }
                } else if (itemCount[0] > 0) {
                    adjust -= 100;
                    itemCount[3]--;
                    itemCount[2]--;
                } else {
                    adjust -= 50;
                    itemCount[3]--;
                    itemCount[2]--;
                }
            } else {
                if (itemCount[1] > 0) {
                    if (itemCount[0] > 0) {
                        adjust -= 100;
                        itemCount[4]--;
                        itemCount[2]--;
                    } else {
                        adjust -= 50;
                        itemCount[4]--;
                        itemCount[2]--;
                    }
                } else if (itemCount[0] > 0) {
                    adjust -= 50;
                    itemCount[4]--;
                    itemCount[2]--;
                } else {
                    adjust -= 25;
                    itemCount[4]--;
                    itemCount[2]--;
                }
            }

        }
        return adjust;
    }

    public int getConsumption(){
        return totalConsumption + menuSet();
    }
}
