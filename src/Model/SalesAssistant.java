package Model;//Magnus Svendsen DAT16i

public class SalesAssistant extends Employee
{
    
    private String positionName;

    public SalesAssisten(String name, int cpr, Date dateOfBirth, String address, int phoneNumber, String email, String positionName){

        super(name,cpr,dateOfBirth,address, phoneNumber,email);
        positionName = this.positionName;

    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String toSting(){

        return super.toString() +
                "\nPosition:" + this.positionName;

    }

}
