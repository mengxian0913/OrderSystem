package org.example;

public class Membership {
    private String phoneNumber;
    private String name;
    private int coupon15off;
    private int coupon10off;
    private int accumulatedPrice;
    private boolean isExchange10off;

    Membership(String phone, String name) {
        this.phoneNumber = phone;
        this.name = name;
        coupon15off = 0;
        coupon10off = 0;
        accumulatedPrice = 0;
        isExchange10off = false;
    }

    Membership() {
        this.phoneNumber = null;
        this.name = null;
    }

    public void SetInfo(String phone, String name) {
        this.phoneNumber = phone;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getCoupon15off() {
        return coupon15off;
    }

    public int getCoupon10off() {
        return coupon10off;
    }

    public void addCoupon15off() {
        coupon15off++;
    }

    public void addCoupon10off() {
        coupon10off++;
    }

    public void subCoupon15off() {
        coupon15off--;
    }

    public void subCoupon10off() {
        coupon10off--;
    }

    public void totalConsumption(int price) {
        accumulatedPrice += price;
        if (isExchange10off) {
            if (accumulatedPrice >= 10000) {
                accumulatedPrice -= 10000;
                addCoupon15off();
                isExchange10off = false;
            }
        }
        if (!isExchange10off) {
            if (accumulatedPrice >= 5000) {
                addCoupon10off();
                isExchange10off = true;
            }
        }
    }

}