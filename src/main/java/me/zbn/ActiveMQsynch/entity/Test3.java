package me.zbn.ActiveMQsynch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Test3{
    @Id
    private int id;
	private String telephone;

	private String email;

	private String address;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
