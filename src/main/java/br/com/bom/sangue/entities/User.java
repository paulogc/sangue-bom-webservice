package br.com.bom.sangue.entities;

import java.util.Date;

public class User {
	
    private Long id;
    private String name;
    private String email;
    private Date birthdate;
    private Address address;
    
    public User () {
    	
    }
    
    public User (String name, String email, Date birthdate) {
    	this.name = name;
    	this.email = email;
    	this.birthdate = birthdate;
    }

    public User (Long id, String name, String email, Date birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
