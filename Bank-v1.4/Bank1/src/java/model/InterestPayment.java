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
public class InterestPayment {
    private long id;
    private String typeIP;

    public InterestPayment(long id, String typeIP) {
        this.id = id;
        this.typeIP = typeIP;
    }

    public InterestPayment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeIP() {
        return typeIP;
    }

    public void setTypeIP(String typeIP) {
        this.typeIP = typeIP;
    }
    
    
}
