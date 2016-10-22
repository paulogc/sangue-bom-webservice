package br.com.bom.sangue.entities;

import java.util.Date;

public class Administrator extends User {
    private String password;

    public Administrator() {}

    public Administrator(Long id, String name, String email, Date birthdate, Address address, Telephone telephone,
                        String password) {
        super(id, name, email, birthdate, address, telephone);
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
