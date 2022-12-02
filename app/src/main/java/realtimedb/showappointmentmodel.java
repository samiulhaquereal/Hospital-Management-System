package realtimedb;

public class showappointmentmodel {

    String name,date,phone,doctorname;

    public showappointmentmodel(String name, String date, String phone, String doctorname) {
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.doctorname = doctorname;
    }

    public showappointmentmodel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }
}
