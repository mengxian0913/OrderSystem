import java.util.ArrayList;

public class System {
    private final ArrayList <Membership> member;

    System(){
        member= new ArrayList<>();
    }

    public void addMembership(String phone,String name){
        member.add(new Membership(phone,name));
    }

    public boolean isMembership(String phone){
        boolean isMembership=false;
        for (Membership temp : member){
            if (phone.equals(temp.getPhoneNumber())) {
                isMembership = true;
                break;
            }
        }
        return isMembership;
    }

}
