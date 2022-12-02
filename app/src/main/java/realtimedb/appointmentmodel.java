package realtimedb;

public class appointmentmodel {
    String namet,phonet,userGender,dateet,docnam;

    public appointmentmodel(){}

    public appointmentmodel(String namet, String phonet, String userGender, String dateet, String docnam) {
        this.namet = namet;
        this.phonet = phonet;
        this.userGender = userGender;
        this.dateet = dateet;
        this.docnam = docnam;
    }

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
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

    public String getDocnam() {
        return docnam;
    }

    public void setDocnam(String docnam) {
        this.docnam = docnam;
    }
}
