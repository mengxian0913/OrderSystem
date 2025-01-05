package org.example;

import java.util.ArrayList;

public class Order {
    private int totalConsumption;
    private final ArrayList <OrderList> orderList;

    Order(){
        totalConsumption = 0;
        orderList = new ArrayList<>();
    }

    public void orderMenu(String []menu){
        for(String menuName : menu){
            assert menuName.length()==1 : "菜單代號有誤";
            switch (menuName){
                case "A":
                    orderList.add(OrderList.A);
                    break;
                case "B":
                    orderList.add(OrderList.B);
                    break;
                case "C":
                    orderList.add(OrderList.C);
                    break;
                case "D":
                    orderList.add(OrderList.D);
                    break;
                case "E":
                    orderList.add(OrderList.E);
                    break;
                case "F":
                    orderList.add(OrderList.F);
                    break;
                case "G":
                    orderList.add(OrderList.G);
                    break;
                case "H":
                    orderList.add(OrderList.H);
                    break;
                default:
                    break;
            }
        }
    }

    public void orderConsumption(){
        for(OrderList item: orderList){
            this.totalConsumption+=item.getPrice();
        }
    }
    public int getConsumption(){
        return totalConsumption;
    }
}
