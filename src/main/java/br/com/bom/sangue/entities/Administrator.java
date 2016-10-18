package br.com.bom.sangue.entities;

public class Administrator extends User {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
