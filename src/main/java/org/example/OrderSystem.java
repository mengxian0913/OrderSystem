package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;


public class OrderSystem {
    private final ArrayList<Membership> member;

    OrderSystem() {
        member = new ArrayList<>();
    }

    public void addMembership(String phone, String name) {
        member.add(new Membership(phone, name));
    }

//    public boolean isMembership(String phone, String name) {
//        boolean isMembership = false;
//        for (Membership temp : member) {
//            if (phone.equals(temp.getPhoneNumber()) && name.equals(temp.getName())) {
//                isMembership = true;
//                break;
//            }
//        }
//        return isMembership;
//    }

    public Membership isMembership(String phone, String name) {
        Membership tempVar = new Membership();
        for (Membership temp : member) {
            if (phone.equals(temp.getPhoneNumber()) && name.equals(temp.getName())) {
                tempVar=temp;
                break;
            }
        }
        return tempVar;
    }

    public int coupon(int amount, String phone, String name, String timePeriod, boolean isBirth, boolean isEmploy, boolean isTeach, boolean useCoupon) {
        DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("u-M-d HH", Locale.ENGLISH);
        DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);
        LocalDate t = LocalDate.parse(timePeriod, dtfInput);
        Membership memberInfo = isMembership(phone,name);
        double price = amount;
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
        } else if (useCoupon) {
            if (memberInfo.getCoupon15off() > 0) {
                memberInfo.subCoupon15off();
                price *= 0.85;
            } else if (memberInfo.getCoupon10off() > 0) {
                memberInfo.subCoupon10off();
                price *= 0.9;
            }
        }
        if (night || weekend) {
            price *= 1.15;
        } else {
            price *= 1.1;
        }
        memberInfo.totalConsumption((int) Math.ceil(price));

        return (int) Math.ceil(price);
    }

}
