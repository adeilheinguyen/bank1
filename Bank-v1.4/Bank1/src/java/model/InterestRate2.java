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
public class InterestRate2 {
   
    private long id;
    private TypePassbook2 tp2;
    private int kyHanGui ;
    private String soTienGui;
    private double laiSuat;

    public InterestRate2() {
    }

    public InterestRate2(long id, TypePassbook2 tp2, int kyHanGui, String soTienGui, double laiSuat) {
        this.id = id;
        this.tp2 = tp2;
        this.kyHanGui = kyHanGui;
        this.soTienGui = soTienGui;
        this.laiSuat = laiSuat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TypePassbook2 getTp2() {
        return tp2;
    }

    public void setTp2(TypePassbook2 tp2) {
        this.tp2 = tp2;
    }

    public int getKyHanGui() {
        return kyHanGui;
    }

    public void setKyHanGui(int kyHanGui) {
        this.kyHanGui = kyHanGui;
    }

    public String getSoTienGui() {
        return soTienGui;
    }

    public void setSoTienGui(String soTienGui) {
        this.soTienGui = soTienGui;
    }

    public double getLaiSuat() {
        return laiSuat;
    }

    public void setLaiSuat(double laiSuat) {
        this.laiSuat = laiSuat;
    }
    
    
}
