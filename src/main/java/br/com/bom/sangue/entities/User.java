package br.com.bom.sangue.entities;

import java.util.Date;

public class User {
	
    private Long id;
    private String name;
    private String email;
    private Date birthDate;
    private Address address;
    private Telephone telephone;

    public User () {
    	
    }
    
    public User (String name, String email, Date birthDate, Address address, Telephone telephone) {
    	this.name = name;
    	this.email = email;
    	this.birthDate = birthDate;
    	this.address = address;
        this.telephone = telephone;
    }

    public User (Long id, String name, String email, Date birthDate, Address address, Telephone telephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
}
