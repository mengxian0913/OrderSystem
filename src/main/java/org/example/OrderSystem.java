package org.example;

import java.util.ArrayList;

/**
 * 點餐系統Class，存放會員資料與桌號資訊
 */
public class OrderSystem {
    /**
     * 會員資料(如同database)
     */
    private final ArrayList<Membership> member;
    /**
     * 紀錄桌號的狀態，連結到Class.Table
     * 桌號只有12個
     */
    private final Table[] tableNo;

    /**
     * OrderSystem建構子 & 初始化
     */
    protected OrderSystem() {
        member = new ArrayList<>();
        tableNo = new Table[12];
        for(int i=0;i<12;i++){
            tableNo[i] = new Table();
        }
    }

    /**
     * 新增會員(初始化設定新增)
     * @param phone 傳參數用來新增會員資料-電話
     * @param name  傳參數用來新增會員資料-名稱
     */
    public void addMembership(final String phone,final String name) {
        member.add(new Membership(phone, name));
    }

    /**
     * 新增會員(使用Membership直接添增)
     * @param member 傳參數用來直接新增會員
     */
    public void addMembership(final Membership member) {
        this.member.add(member);
    }

    /**
     * 修改會員資料
     * @param oldPhone 傳參數去對比會員的電話
     * @param oldName 傳參數去對比會員的名稱
     * @param newInfo 傳參數去修改會員的新資料  -> [0]=phoneNumber  [1]=name
     */
    public void modifyMemberInfo(final String oldPhone,final String oldName,final String []newInfo){
        for (final Membership temp : member) {
            if (oldPhone.equals(temp.getPhoneNumber()) && oldName.equals(temp.getName())) {
                temp.setInfo(newInfo[0],newInfo[1]);
                break;
            }
        }
    }

    /**
     * 尋找是否有這個會員資料，如果有的將會回傳Class.Membership型態的資料
     * @param phone 傳參數去對比會員的電話
     * @param name 傳參數去對比會員的名稱
     * @return 找到的會員資料
     */
    public Membership findMembership(final String phone, final String name) {
        Membership tempVar = new Membership();
        for (final Membership temp : member) {
            if (phone.equals(temp.getPhoneNumber()) && name.equals(temp.getName())) {
                tempVar = temp;
                break;
            }
        }
        return tempVar;
    }

    /**
     * 訂位桌號與會員資訊的輸入，如果桌號已經被訂位了，將會報錯
     * @param phone 傳參數去對比會員的電話，如果無則填入""
     * @param name 傳參數去對比會員的名稱，如果無則填入""
     * @param tableNumber 傳參數去選擇桌號
     */
    public void booking(final String phone,final String name,final int tableNumber)  {
        if(tableNo[tableNumber-1].tableStatus()){
            throw new AssertionError("這個位置已經被訂位了");
        }
        final Membership memberInfo = findMembership(phone, name);
        if (memberInfo.getPhoneNumber() != null) {
            tableNo[tableNumber - 1] = new Table(memberInfo);
            tableNo[tableNumber - 1].bookingStatus();
        } else {
            tableNo[tableNumber - 1] = new Table();
            tableNo[tableNumber - 1].bookingStatus();
        }
    }

    /**
     * 針對桌號而進行點餐的行為，需一個一個餐品名稱點餐
     * @param index 傳參數代表餐點的名稱
     * @param tableNumber 傳參數代表點餐的桌號
     */
    public void order(final String index,final int tableNumber){
        tableNo[tableNumber - 1].orderFood(index);
    }

    /**
     * 進行結帳的行為，需要出示任何打折的資料
     * 結帳完，桌號就會可以訂位
     * @param tableNumber 傳參數代表結帳的桌號     @param time 傳參數代表結帳的時間
     * @param isBirth 傳參數代表是否有壽星    @param isEmploy 傳參數代表是否是員工
     * @param isTeach 傳參數代表是否為合作教職園與合作校園學生    @param useCoupon 傳參數代表是否會員有需要使用折價券
     * @return 最終的結帳金額
     */
    public int checkout(final int tableNumber,final String time,final boolean isBirth,final boolean isEmploy,final boolean isTeach,final boolean useCoupon)  {
        tableNo[tableNumber-1].restoreStatus();
        tableNo[tableNumber-1].submitOrder();
        return tableNo[tableNumber-1].coupon(time,isBirth,isEmploy,isTeach,useCoupon);
    }


}
