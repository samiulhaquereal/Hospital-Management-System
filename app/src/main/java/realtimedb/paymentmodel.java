package realtimedb;

public class paymentmodel {
    String patientid,transactionid,label;
    public paymentmodel(){}

    public paymentmodel(String patientid, String transactionid, String label) {
        this.patientid = patientid;
        this.transactionid = transactionid;
        this.label = label;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
