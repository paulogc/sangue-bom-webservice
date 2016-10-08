package br.com.bom.sangue.entities;


import java.util.Date;

public class IntentDonation {
    private Long id;
    private Date createdAt;
    private Boolean active;
    private Date grantDate;
    private Long bloodDonator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(Date createAt) {
        this.createdAt = createAt;
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

    public Long getBloodDonator() {
        return bloodDonator;
    }

    public void setBloodDonator(Long bloodDonator) {
        this.bloodDonator = bloodDonator;
    }
}
