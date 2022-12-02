package realtimedb;

public class addpatientmodel {
    String namet,addresst,phonet,userGender,dateet,bedt;

    public addpatientmodel(){}

    public addpatientmodel(String namet, String addresst, String phonet, String userGender, String dateet, String bedt) {
        this.namet = namet;
        this.addresst = addresst;
        this.phonet = phonet;
        this.userGender = userGender;
        this.dateet = dateet;
        this.bedt = bedt;
    }

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    public String getAddresst() {
        return addresst;
    }

    public void setAddresst(String addresst) {
        this.addresst = addresst;
    }

    public String getPhonet() {
        return phonet;
    }

    public void setPhonet(String phonet) {
        this.phonet = phonet;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getDateet() {
        return dateet;
    }

    public void setDateet(String dateet) {
        this.dateet = dateet;
    }

    public String getBedt() {
        return bedt;
    }

    public void setBedt(String bedt) {
        this.bedt = bedt;
    }
}
