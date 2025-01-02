public enum OrderList {
    A("Double Chocolate Cake",500),
    B("Burnt Cream",300),
    C("Mousse Cake",250),
    D("Brownie",300),
    E("Caesar salad",300),
    F("Greek salad",250),
    G("House salad",200);

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

