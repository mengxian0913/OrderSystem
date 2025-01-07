package org.example;

/**
 * 處理套餐組合class，紀錄餐點種類的數量與優惠折扣
 */
public class MenuCombo {
    /**
     * 紀錄餐點種類的數量
     */
    private final int [] amount = new int[6];
    /**
     * 紀錄餐點套餐的折扣
     */
    private int adjustPrice;

    /**
     * MenuCombo建構子 & 初始化
     * @param array 傳參數至class variable:amount
     */
    protected MenuCombo(final int []array ){
        System.arraycopy(array, 0, amount, 0, 6);
        adjustPrice=0;
    }

    /**
     * 去計算下方兩種折扣:
     * pasta+drink=20
     * steak+drink=20
     */
    public void comboA(){
        while(amount[0]>0  && amount[4]>0){
            amount[0]--;
            amount[4]--;
            adjustPrice+=20;
        }
        while(amount[1]>0  && amount[4]>0){
            amount[1]--;
            amount[4]--;
            adjustPrice+=20;
        }
    }

    /**
     * 去計算下方兩種折扣:
     * pasta+appetizer=40
     * steak+appetizer=40
     */
    public void comboB(){
        while(amount[0]>0  && amount[5]>0){
            amount[0]--;
            amount[5]--;
            adjustPrice+=40;
        }
        while(amount[1]>0  && amount[5]>0){
            amount[1]--;
            amount[5]--;
            adjustPrice+=40;
        }
    }

    /**
     * 去計算下方兩種折扣:
     * pasta+salad+drink=50
     * steak+salad+drink=50
     */
    public void comboC(){
        while(amount[0]>0  && amount[3]>0 && amount[4]>0){
            amount[0]--;
            amount[3]--;
            amount[4]--;
            adjustPrice+=50;
        }
        while(amount[1]>0  && amount[3]>0 && amount[4]>0){
            amount[1]--;
            amount[3]--;
            amount[4]--;
            adjustPrice+=50;
        }
    }

    /**
     * 去計算下方兩種折扣:
     * pasta+appetizer+drink=70
     * steak+appetizer+drink=70
     */
    public void comboD(){
        while(amount[0]>0  && amount[5]>0 && amount[4]>0){
            amount[0]--;
            amount[5]--;
            amount[4]--;
            adjustPrice+=70;
        }
        while(amount[1]>0  && amount[5]>0 && amount[4]>0){
            amount[1]--;
            amount[5]--;
            amount[4]--;
            adjustPrice+=70;
        }
    }

    /**
     * 去計算下方兩種折扣:
     * pasta+dessert+drink=100
     * steak+dessert+drink=100
     */
    public void comboE(){
        while(amount[0]>0  && amount[2]>0 && amount[4]>0){
            amount[0]--;
            amount[2]--;
            amount[4]--;
            adjustPrice+=100;
        }
        while(amount[1]>0  && amount[2]>0 && amount[4]>0){
            amount[1]--;
            amount[2]--;
            amount[4]--;
            adjustPrice+=100;
        }
    }

    /**
     * 去計算下方兩種折扣:
     * pasta+dessert+appetizer+salad+drink=250
     * steak+dessert+appetizer+salad+drink=250
     */
    public void comboF(){
        while((amount[0]>0 || amount[1]>0)  && amount[2]>0 && amount[3]>0 && amount[4]>0 && amount[5]>0){
            if(amount[0]>0){
                amount[0]--;
            }
            else {
                amount[1]--;
            }
            amount[2]--;
            amount[3]--;
            amount[4]--;
            amount[5]--;
            adjustPrice+=250;
        }
    }

    /**
     * 查閱套餐折扣的總額度
     * @return 折扣金額
     */
    public int getAdjustPrice(){
        return adjustPrice;
    }


}
