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
public class TypePassbook2 {
    private int id;
    private String tentk;

    public TypePassbook2() {
    }

    public TypePassbook2(int id, String tentk) {
        this.id = id;
        this.tentk = tentk;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }
    
}
