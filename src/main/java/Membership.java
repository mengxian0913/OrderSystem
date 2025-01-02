public class Membership {
    private String phoneNumber;
    private String name;

    Membership(String phone, String name){
        this.phoneNumber=phone;
        this.name=name;
    }
    Membership(){}

    public void SetInfo(String phone, String name){
        this.phoneNumber=phone;
        this.name=name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getName(){
        return name;
    }

}