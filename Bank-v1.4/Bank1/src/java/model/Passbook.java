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
public class Passbook {

    private long id;
    private String name;
    private TypePassbook2 tp2;
    private String openDateSB;
    private String dateExprice;
    private int status;
    public Passbook(long id, String name, TypePassbook2 tp2, String openDateSB, String dateExprice,int status) {
        this.id = id;
        this.name = name;
        this.tp2 = tp2;
        this.openDateSB = openDateSB;
        this.dateExprice = dateExprice;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

    public String getDateExprice() {
        return dateExprice;
    }

    public void setDateExprice(String dateExprice) {
        this.dateExprice = dateExprice;
    }

    public Passbook() {
    }

    public TypePassbook2 getTp2() {
        return tp2;
    }

    public void setTp2(TypePassbook2 tp2) {
        this.tp2 = tp2;
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

    public String getOpenDateSB() {
        return openDateSB;
    }

    public void setOpenDateSB(String openDateSB) {
        this.openDateSB = openDateSB;
    }

}
