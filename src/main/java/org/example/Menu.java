package org.example;

/**
 * 菜單Enum，去記錄餐點與價格
 */
public enum Menu {
    /**
     * 下方為餐點內容(可擴增)
     */
    /*pasta type*/
    PESTO_PASTA("Pesto Pasta",400),
    GARLIC_PASTA("Garlic Pasta",400),
    CREAMY_PASTA("Creamy Pasta",400),
    TOMATO_PASTA("Tomato Pasta",400),
    /*steak type*/
    BEEF_STEAK("Beef Steak",500),
    PORK_CHOP("Pork Chop",500),
    CHICKEN_CUTLET("Chicken Cutlet",500),
    LAMB_CHOP("Lamb Chop",500),
    /*dessert type*/
    CHOCOLATE_CAKE("Chocolate Cake",500),
    BURNT_CREAM("Burnt Cream",300),
    MOUSSE_CAKE("Mousse Cake",250),
    BROWNIE("Brownie",300),
    /*salad type*/
    CAESAR_SALAD("Caesar Salad",300),
    GREEK_SALAD("Greek Salad",250),
    HOUSE_SALAD("House Salad",200),
    /*drink type*/
    BLACK_TEA("Black Tea",50),
    MILK_TEA("Milk Tea",70),
    ORANGE_JUICE("Orange Juice",150),
    LATTE("Latte",150),
    AMERICANO("Americano",100),
    COLA("Cola",100),
    BEER("Beer",150),
    /*appetizer type*/
    FRIES("Fries",150),
    KARAAGE("Karaage",150),
    CHICKEN_NUGGETS("Chicken Nuggets",150),
    GARLIC_BREAD("Garlic Bread",150),
    CHEESE_STICKS("Cheese Sticks",150);

    /**
     * 紀錄餐點的價格
     */
    private final int dishPrice;
    /**
     * 紀錄餐點的名稱
     */
    private final String dishName;

    /**
     * 建立Enum之時，利用上面建立好的表，去傳參數價格
     * @param orderPrice 傳參數至class variable:dishPrice
     */
    Menu(final String orderName,final int orderPrice) {
        this.dishPrice=orderPrice;
        this.dishName=orderName;
    }

    /**
     * 取得餐點的價格
     * @return 該餐點其價格
     */
    public int getDishPrice(){
        return dishPrice;
    }

    /**
     * 取得餐點的名稱
     * @return 該餐點其價格
     */
    public String getDishName(){
        return dishName;
    }
}