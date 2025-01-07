package org.example;

import java.util.ArrayList;

/**
 * 點餐處理class，去記錄總消費金額與餐點列表
 */
public class Order {
    /**
     * 餐點總消費金額
     */
    private int totalConsumption;
    /**
     * 紀錄餐點列表，連結到Enum.Menu
     */
    private final ArrayList<Menu> orderList;

    /**
     * Order建構子 & 初始化
     */
    protected Order() {
        totalConsumption = 0;
        orderList = new ArrayList<>();
    }

    /**
     * 將點餐內容去查閱Menu是否存在
     * 是->存入orderList紀錄餐點  否->報錯
     * @param dishNameList 參數為Class.Table桌號記錄所有的點餐內容
     */
    public void orderMenu(final ArrayList<String> dishNameList)  {
        boolean isErrorName;
        for (final String menuName : dishNameList) {
            isErrorName=true;
            for(final Menu menu : Menu.values()){
                if(menuName.equals(menu.getDishName())){
                    orderList.add(menu);
                    isErrorName=false;
                    break;
                }
            }
            if(isErrorName){
                throw new AssertionError("餐點名稱有誤");
            }
        }
    }

    /**
     * 將餐點列表的總金額加總，並跑入menuSet判斷套餐折扣
     */
    public void orderConsumption() {
        for (final Menu item : orderList) {
            this.totalConsumption += item.getDishPrice();
        }
        totalConsumption = totalConsumption - menuSet();
    }

    /**
     * 運算餐點列表的種類數量，並去計算是否有套餐折扣
     * 連結到Class.MenuCombo
     * @return 套餐折扣的總金額
     */
    private int menuSet() {
        final int[] itemCount = getItemAmount();
        int adjust = 0;
        if (itemCount[0] > 0 || itemCount[1] > 0 ) {
            final MenuCombo combo = new MenuCombo(itemCount);
            combo.comboF();
            combo.comboE();
            combo.comboD();
            combo.comboC();
            combo.comboB();
            combo.comboA();
            adjust=combo.getAdjustPrice();
        }
        return adjust;
    }

    /**
     * 計算各種類的餐點數量
     * 0.Pasta  1.Steak  2.Dessert
     * 3.Salad  4.Drink  5.Appetizer
     * @return 各種類的餐點數量
     */
    private int[] getItemAmount() {
        final int[] amount = new int[6];
        amount[0]=getPastaAmount();
        amount[1]=getSteakAmount();
        amount[2]=getDessertAmount();
        amount[3]=getSaladAmount();
        amount[4]=getDrinkAmount();
        amount[5]=getAppetizerAmount();
        return amount;
    }

    /**
     * 計算pasta種類的數量
     * @return 此種類的數量
     */
    private int getPastaAmount(){
        int count=0;
        for (final Menu item : orderList) {
            if(item.equals(Menu.PESTO_PASTA) || item.equals(Menu.GARLIC_PASTA) || item.equals(Menu.CREAMY_PASTA) || item.equals(Menu.TOMATO_PASTA)){
                count++;
            }
        }
        return count;
    }

    /**
     * 計算steak種類的數量
     * @return 此種類的數量
     */
    private int getSteakAmount(){
        int count=0;
        for (final Menu item : orderList) {
            if(item.equals(Menu.BEEF_STEAK) || item.equals(Menu.PORK_CHOP) || item.equals(Menu.CHICKEN_CUTLET) || item.equals(Menu.LAMB_CHOP)){
                count++;
            }
        }
        return count;
    }

    /**
     * 計算dessert種類的數量
     * @return 此種類的數量
     */
    private int getDessertAmount(){
        int count=0;
        for (final Menu item : orderList) {
            if(item.equals(Menu.CHOCOLATE_CAKE) || item.equals(Menu.BURNT_CREAM) || item.equals(Menu.MOUSSE_CAKE) || item.equals(Menu.BROWNIE)){
                count++;
            }
        }
        return count;
    }

    /**
     * 計算salad種類的數量
     * @return 此種類的數量
     */
    private int getSaladAmount(){
        int count=0;
        for (final Menu item : orderList) {
            if(item.equals(Menu.GREEK_SALAD) || item.equals(Menu.CAESAR_SALAD) || item.equals(Menu.HOUSE_SALAD)){
                count++;
            }
        }
        return count;
    }

    /**
     * 計算drink種類的數量
     * @return 此種類的數量
     */
    private int getDrinkAmount(){
        int count=0;
        for (final Menu item : orderList) {
            if(item.equals(Menu.BLACK_TEA) || item.equals(Menu.MILK_TEA) || item.equals(Menu.ORANGE_JUICE) || item.equals(Menu.LATTE)|| item.equals(Menu.AMERICANO) || item.equals(Menu.COLA) || item.equals(Menu.BEER)){
                count++;
            }
        }
        return count;
    }

    /**
     * 計算Appetizer種類的數量
     * @return 此種類的數量
     */
    private int getAppetizerAmount(){
        int count=0;
        for (final Menu item : orderList) {
            if(item.equals(Menu.FRIES) || item.equals(Menu.KARAAGE) || item.equals(Menu.CHICKEN_NUGGETS) || item.equals(Menu.GARLIC_BREAD) || item.equals(Menu.CHEESE_STICKS)){
                count++;
            }
        }
        return count;
    }

    /**
     * 查閱此次的消費餐點總金額
     * @return 此餐點列表的總金額
     */
    public int getConsumption() {
        return totalConsumption;
    }
}
