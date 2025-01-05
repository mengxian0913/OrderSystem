package org.example;

public enum OrderList {
    A("Double Chocolate Cake",500),
    B("Burnt Cream",300),
    C("Mousse Cake",250),
    D("Brownie",300),
    E("Caesar salad",300),
    F("Greek salad",250),
    G("House salad",200),
    H("orange juice",150),
    I("latte",150),
    J("cola",100),
    K("beer",150),
    L("steak",400),
    M("pizza",300),
    N("pasta",300),
    O("fries",150);

    private final String orderName;
    private final int orderPrice;
    OrderList( String orderName,int orderPrice) {
        this.orderName=orderName;
        this.orderPrice=orderPrice;
    }
    public int getPrice(){
        return orderPrice;
    }
    public String getName(){
        return orderName;
    }

}