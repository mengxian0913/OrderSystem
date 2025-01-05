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
        isBook = true;
        isMember = false;
        orderInfo = new Order();
        orderList = new ArrayList<>();
    }

    Table(Membership member) {
        isBook = true;
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

    public void submitOrder() throws Exception {
        orderInfo.orderMenu(orderList);
        orderInfo.orderConsumption();
    }
    public void restoreStatus(){
        isBook=false;
    }

    public int coupon(String timePeriod, boolean isBirth, boolean isEmploy, boolean isTeach, boolean useCoupon) {
        DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("u-M-d HH", Locale.ENGLISH);
        DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);
        LocalDate t = LocalDate.parse(timePeriod, dtfInput);
        double price = orderInfo.getConsumption();
        boolean night = false, weekend = false;

        if (t.format(dtfOutput).equals("Saturday") || t.format(dtfOutput).equals("Sunday")) {
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
        memberInfo.totalConsumption((int) Math.ceil(price));

        return (int) Math.ceil(price);
    }
}
