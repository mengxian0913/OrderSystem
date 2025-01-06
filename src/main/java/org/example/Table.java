package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;

public class Table {
    private boolean isBook;
    private final boolean isMember;
    private final Order orderInfo;
    private Membership memberInfo;
    private final ArrayList<String> orderList;

    Table() {
        isBook = false;
        isMember = false;
        orderInfo = new Order();
        orderList = new ArrayList<>();
    }

    Table(Membership member) {
        isBook = false;
        isMember = true;
        memberInfo = member;
        orderInfo = new Order();
        orderList = new ArrayList<>();
    }

    public boolean tableStatus() {
        return isBook;
    }

    public void orderFood(String index) {
        orderList.add(index);
    }

    public void submitOrder() {
        orderInfo.orderMenu(orderList);
        orderInfo.orderConsumption();
    }
    public void bookingStatus(){
        isBook=true;
    }
    public void restoreStatus(){
        isBook=false;
    }

    public int coupon(final String timePeriod,final boolean isBirth,final boolean isEmploy,final boolean isTeach,final boolean useCoupon) {
        final DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("u-M-d HH", Locale.ENGLISH);
        final DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);
        final LocalDate time = LocalDate.parse(timePeriod, dtfInput);
        double price = orderInfo.getConsumption();
        boolean night = false, weekend = false;

        if ("Saturday".equals(time.format(dtfOutput)) || "Sunday".equals(time.format(dtfOutput))) {
            weekend = true;
        }
        if (dtfInput.parse(timePeriod).get(ChronoField.HOUR_OF_DAY) >= 18) {
            night = true;
        }

        if (isEmploy) {
            price *= 0.7;
        } else if (isBirth) {
            price *= 0.8;
        } else if (isTeach) {
            price *= 0.85;
        } else if (useCoupon && isMember) {
            if (memberInfo.getCoupon15off() > 0) {
                memberInfo.subCoupon15off();
                price *= 0.85;
            } else if (memberInfo.getCoupon10off() > 0) {
                memberInfo.subCoupon10off();
                price *= 0.9;
            }
        }
        if (!isEmploy) {
            if (night || weekend) {
                price *= 1.15;
            } else {
                price *= 1.1;
            }
        }
        if(isMember) {
            memberInfo.addConsumption((int) Math.ceil(price));
        }
        return (int) Math.ceil(price);
    }
}
