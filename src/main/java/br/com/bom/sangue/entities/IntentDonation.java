package br.com.bom.sangue.entities;


import java.util.Date;

public class IntentDonation {
    private Long id;
    private Date createdDate;
    private Boolean active;
    private Date grantDate;
    private BloodDonator bloodDonator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public BloodDonator getBloodDonator() {
        return bloodDonator;
    }

    public void setBloodDonator(BloodDonator bloodDonator) {
        this.bloodDonator = bloodDonator;
    }
}
