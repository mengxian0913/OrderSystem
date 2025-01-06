package org.example;

import java.util.ArrayList;

public class OrderSystem {
    private final ArrayList<Membership> member;
    private final Table[] tableNo;

    OrderSystem() {
        member = new ArrayList<>();
        tableNo = new Table[12];
        for(int i=0;i<12;i++){
            tableNo[i] = new Table();
        }
    }

    public void addMembership(String phone, String name) {
        member.add(new Membership(phone, name));
    }
    public void addMembership(Membership member) {
        this.member.add(member);
    }

    public void modifyMemberInfo(String oldPhone,String oldName,String newPhone,String newName){
        for (Membership temp : member) {
            if (oldPhone.equals(temp.getPhoneNumber()) && oldName.equals(temp.getName())) {
                temp.setInfo(newPhone,newName);
                break;
            }
        }
    }

    public Membership isMembership(String phone, String name) {
        Membership tempVar = new Membership();
        for (Membership temp : member) {
            if (phone.equals(temp.getPhoneNumber()) && name.equals(temp.getName())) {
                tempVar = temp;
                break;
            }
        }
        return tempVar;
    }


    public void booking(String phone, String name, int tableNumber)  {
        if(tableNo[tableNumber-1].tableStatus()){
            throw new AssertionError("這個位置已經被訂位了");
        }
        Membership memberInfo = isMembership(phone, name);
        if (memberInfo.getPhoneNumber() != null) {
            tableNo[tableNumber - 1] = new Table(memberInfo);
            tableNo[tableNumber - 1].bookingStatus();
        } else {
            tableNo[tableNumber - 1] = new Table();
            tableNo[tableNumber - 1].bookingStatus();
        }
    }

    public void order(String index,int tableNumber){
        tableNo[tableNumber - 1].orderFood(index);
    }

    public int checkout(int tableNumber,String time,boolean isBirth, boolean isEmploy, boolean isTeach, boolean useCoupon)  {
        tableNo[tableNumber-1].restoreStatus();
        tableNo[tableNumber-1].submitOrder();
        return tableNo[tableNumber-1].coupon(time,isBirth,isEmploy,isTeach,useCoupon);
    }


}
