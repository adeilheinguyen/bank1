/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Admin
 */
public abstract class Member {
    private long id;
    private AccountIB aib;
    private String name;
    private String email;
    private String date;
    private String address;
    private String CMT;
    private String phone;

    public Member() {
    }

    public Member(long id, AccountIB aib,String name, String email, String date,String address, String CMT,String phone) {
        this.id = id;
        this.aib = aib;
        this.name = name;
        this.email = email;
        this.date = date;
        this.CMT = CMT;
        this.address= address;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCMT() {
        return CMT;
    }

    public void setCMT(String CMT) {
        this.CMT = CMT;
    }

    
    public AccountIB getAib() {
        return aib;
    }

    public void setAib(AccountIB aib) {
        this.aib = aib;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
