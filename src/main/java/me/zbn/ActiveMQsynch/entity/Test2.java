package me.zbn.ActiveMQsynch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test2{
    @Id
    private int id;
	private String subid;

	private String meid;

	private String acid;

	private String acname;

	private String number;

	private String name;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public String getAcname() {
        return acname;
    }

    public void setAcname(String acname) {
        this.acname = acname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
