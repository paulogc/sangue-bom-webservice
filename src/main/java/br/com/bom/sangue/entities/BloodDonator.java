package br.com.bom.sangue.entities;


import java.util.Date;

public class BloodDonator extends User {
    private String bloodType;
    private String bloodFactor;
    private String cpf;
    private String nickname;

    public BloodDonator() {}

    public BloodDonator(Long id, String name, String email, Date birthdate, Address address, Telephone telephone,
                        String bloodType, String bloodFactor, String cpf, String nickname) {
        super(id, name, email, birthdate, address, telephone);
        this.bloodType = bloodType;
        this.bloodFactor = bloodFactor;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }
}
