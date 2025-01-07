package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;

/**
 * 桌號資訊Class，紀錄桌位點餐內容、定位資訊與桌位訂位狀態
 */
public class Table {
    /**
     * 定義常數，晚上6點視為晚上服務費加成的時刻
     */
    private static final int EVENING_HOUR = 18;
    /**
     * 桌號的訂位狀態
     */
    private boolean isBook;
    /**
     * 訂位是否有填寫會員資料
     */
    private final boolean isMember;
    /**
     * 餐點列表，連結到Class.Order
     */
    private final Order orderInfo;
    /**
     * 紀錄會員資料，連結到Class.Membership
     */
    private Membership memberInfo;
    /**
     * 存放點餐的內容
     */
    private final ArrayList<String> orderList;

    /**
     * Table建構子 & 初始化
     */
    protected Table() {
        isBook = false;
        isMember = false;
        orderInfo = new Order();
        orderList = new ArrayList<>();
    }

    /**
     * Table建構子 & 初始化
     * @param member 傳參數到class variable:memberInfo
     */
    protected Table(final Membership member) {
        isBook = false;
        isMember = true;
        memberInfo = member;
        orderInfo = new Order();
        orderList = new ArrayList<>();
    }

    /**
     * 查看此桌子是否被訂位了
     * @return 桌號訂位狀態
     */
    public boolean tableStatus() {
        return isBook;
    }

    /**
     * 將點餐的內容儲存
     * @param index 參數為點餐的單一餐點
     */
    public void orderFood(final String index) {
        orderList.add(index);
    }

    /**
     * 將存好的全部餐點，使用Class.Order.orderMenu去判斷餐點項目
     * 並計算餐點的全部金額
     */
    public void submitOrder() {
        orderInfo.orderMenu(orderList);
        orderInfo.orderConsumption();
    }

    /**
     * 變更桌號狀態為已被訂位
     */
    public void bookingStatus() {
        isBook = true;
    }

    /**
     * 變更桌號狀態為未被訂位
     */
    public void restoreStatus() {
        isBook = false;
    }

    /**
     * 判斷是否有打折資格與是否需要多加收服務費
     * @param timePeriod 傳參數去判斷星期與時間
     * @param isBirth 判斷是否有壽星生日   @param isEmploy 判斷是否是員工餐
     * @param isTeach 判斷是否是合作教職園與合作校園學生  @param useCoupon 判斷是否會員有需要使用折價券
     * @return 打折完與加完服務費後的最終金額
     */
    public int coupon(final String timePeriod, final boolean isBirth, final boolean isEmploy,
                      final boolean isTeach, final boolean useCoupon) {
        double price = orderInfo.getConsumption();
        final boolean needExtraPay = isNightOrWeekend(timePeriod);
        if (isEmploy) {
            price *= 0.7;
        } else if (isBirth) {
            price *= 0.8;
        } else if (isTeach) {
            price *= 0.85;
        } else if (useCoupon) {
            price*=useCouponDiscount();
        }
        if (!isEmploy) {
            if (needExtraPay) {
                price *= 1.15;
            } else {
                price *= 1.1;
            }
        }
        if (isMember) {
            memberInfo.addConsumption((int) Math.ceil(price));
        }
        return (int) Math.round(price);
    }

    /**
     * 判斷是否是周末與用餐時間是否是晚上6點之後
     * @param timePeriod 傳參數去判斷時間
     * @return 是否需要額外多加收服務費
     */
    protected boolean isNightOrWeekend(final String timePeriod) {
        final DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("u-M-d HH", Locale.ENGLISH);
        final DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);
        final LocalDate time = LocalDate.parse(timePeriod, dtfInput);
        boolean isExtra = false;

        if ("Saturday".equals(time.format(dtfOutput)) || "Sunday".equals(time.format(dtfOutput))) {
            isExtra = true;
        }
        if (dtfInput.parse(timePeriod).get(ChronoField.HOUR_OF_DAY) >= EVENING_HOUR) {
            isExtra = true;
        }
        return isExtra;
    }

    /**
     * 判斷是否有填寫會員與判斷是否擁有折價券
     * @return 打折的折數
     */
    protected double useCouponDiscount(){
        double discount=1;
        if(isMember){
            if (memberInfo.getCoupon15off() > 0) {
                memberInfo.subCoupon15off();
                discount= 0.85;
            } else if (memberInfo.getCoupon10off() > 0) {
                memberInfo.subCoupon10off();
                discount= 0.9;
            }
        }
        return discount;
    }
}
