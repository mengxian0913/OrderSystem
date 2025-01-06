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
                case "I":
                    orderList.add(OrderList.I);
                    break;
                case "J":
                    orderList.add(OrderList.J);
                    break;
                case "K":
                    orderList.add(OrderList.K);
                    break;
                case "L":
                    orderList.add(OrderList.L);
                    break;
                case "M":
                    orderList.add(OrderList.M);
                    break;
                case "N":
                    orderList.add(OrderList.N);
                    break;
                case "O":
                    orderList.add(OrderList.O);
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

    public int[] getItemAmount() {
        int[] temp = new int[5];
        for(OrderList item: orderList){
            if(item.equals(OrderList.A)||item.equals(OrderList.B)||item.equals(OrderList.C)||item.equals(OrderList.D))
            {temp[0]++;}
            if(item.equals(OrderList.E)||item.equals(OrderList.F)||item.equals(OrderList.G))
            {temp[1]++;}
            if(item.equals(OrderList.H)||item.equals(OrderList.I)||item.equals(OrderList.J)||item.equals(OrderList.K))
            {temp[2]++;}
            if(item.equals(OrderList.L)||item.equals(OrderList.M)||item.equals(OrderList.N))
            {temp[3]++;}
            if(item.equals(OrderList.O))
            {temp[4]++;}
        }
        return temp;
    }

    public int menuset(){
        int[] itemCount = getItemAmount();
        int adjust=0;
        while(itemCount[2]>0&&(itemCount[3]>0||itemCount[4]>0)) {
            if(itemCount[3]>0) {
                if (itemCount[1] > 0) {
                    if (itemCount[0] > 0) {
                        if(itemCount[4] > 0) {
                            adjust -= 175;
                            itemCount[4]--;
                            itemCount[3]--;
                            itemCount[2]--;
                        }
                        else {
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
            }
            else
            {
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
        return totalConsumption + menuset();
    }
}
