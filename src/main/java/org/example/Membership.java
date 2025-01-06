package org.example;

/**
 * 會員資料class，去記錄會員的資料、折價券與消費紀錄
 */
public class Membership {
    /**
     * 會員電話號碼
     */
    private String phoneNumber;
    /**
     * 會員名稱
     */
    private String name;
    /**
     * 85折折價券的數量
     */
    private int coupon15off;
    /**
     * 9折折價券的數量
     */
    private int coupon10off;
    /**
     * 消費累積總額
     */
    private int accumulatedPrice;
    /**
     * 判斷消費累積額度是否已經兌換9折券 -> 是:85折券 否:9折券
     */
    private boolean isExchange10off;

    /**
     * Membership建構子
     * @param phone 傳參至class variable:phoneNumber
     * @param name  傳參至class variable:name
     */
    protected Membership(final String phone, final String name) {
        this.phoneNumber = phone;
        this.name = name;
        coupon15off = 0;
        coupon10off = 0;
        accumulatedPrice = 0;
        isExchange10off = false;
    }

    /**
     * Membership空建構子
     */
    protected Membership() {
        this.phoneNumber = "";
        this.name = "";
        coupon15off = 0;
        coupon10off = 0;
        accumulatedPrice = 0;
        isExchange10off = false;
    }

    /**
     * 重新設定會員資料的電話號碼與名稱
     * param: phone重新指派(存入)至class variable:phoneNumber
     * param: name重新指派(存入)至class variable:name
     */
    public void setInfo(final String phone, final String name) {
        this.phoneNumber = phone;
        this.name = name;
    }

    /**
     * 回傳phoneNumber變數(電話號碼)
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 回傳name變數(名稱)
     */
    public String getName() {
        return name;
    }

    /**
     * 回傳coupon15off變數(85折折價券的數量)
     */
    public int getCoupon15off() {
        return coupon15off;
    }

    /**
     * 回傳coupon10off變數(9折折價券的數量)
     */
    public int getCoupon10off() {
        return coupon10off;
    }

    /**
     * 增加85折價券的數量(表示獲得85折折價券1張)
     */
    public void addCoupon15off() {
        coupon15off++;
    }

    /**
     * 增加9折價券的數量(表示獲得9折折價券1張)
     */
    public void addCoupon10off() {
        coupon10off++;
    }

    /**
     * 減少85折價券的數量(表示使用85折折價券1張)
     */
    public void subCoupon15off() {
        coupon15off--;
    }

    /**
     * 減少9折價券的數量(表示使用9折折價券1張)
     */
    public void subCoupon10off() {
        coupon10off--;
    }

    /**
     * 查閱消費累積總額
     *
     * @return 回傳消費累積總額
     */
    public int getAccumulatedPrice() {
        return accumulatedPrice;
    }

    /**
     * 添增此次的消費至會員消費累積總額
     * 如累積到5,000$，獲得9折折價券一張；如累積到10,000$，獲得85折折價券
     * 獲得到85折折價券會重新計算
     *
     * @param price 此次消費的金額
     */
    public void addConsumption(final int price) {
        accumulatedPrice += price;
        if (isExchange10off && accumulatedPrice >= 10_000) {
            accumulatedPrice -= 10_000;
            this.addCoupon15off();
            isExchange10off = false;
        }

        if (!isExchange10off && accumulatedPrice >= 5000) {
            this.addCoupon10off();
            isExchange10off = true;
        }

    }

}