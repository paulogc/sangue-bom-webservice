package br.com.bom.sangue.entities;


import java.util.Date;

public class BloodDonator extends User {
    private String bloodType;
    private String bloodFactor;
    private String CPF;
    private String nickname;

    public BloodDonator() {}

    public BloodDonator(Long id, String name, String email, Date birthdate, Address address,
                        String bloodType, String bloodFactor, String CPF, String nickname) {
        super(id, name, email, birthdate, address);
        this.bloodType = bloodType;
        this.bloodFactor = bloodFactor;
        this.CPF = CPF;
        this.nickname = nickname;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getBloodFactor() {
        return bloodFactor;
    }

    public void setBloodFactor(String bloodFactor) {
        this.bloodFactor = bloodFactor;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }
}
