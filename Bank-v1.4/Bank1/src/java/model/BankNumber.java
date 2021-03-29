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

//Class BankNumber: Số tài khoản của khách hàng
public class BankNumber {
    private long id;
    private Customer customer;
    private String nameBN;
    private double currentBalance;
    private double availableBalance;
    private String openDateAccount;
    private String lastDealDate;

    public BankNumber(long id, Customer customer, double currentBalance, double availableBalance, 
            String openDateAccount, String lastDealDate,String nameBN) {
        this.id = id;
        this.customer = customer;
        this.currentBalance = currentBalance;
        this.availableBalance = availableBalance;
        this.openDateAccount = openDateAccount;
        this.lastDealDate = lastDealDate;
        this.nameBN = nameBN;
    }

    public String getNameBN() {
        return nameBN;
    }

    public void setNameBN(String nameBN) {
        this.nameBN = nameBN;
    }

    
    public BankNumber() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getOpenDateAccount() {
        return openDateAccount;
    }

    public void setOpenDateAccount(String openDateAccount) {
        this.openDateAccount = openDateAccount;
    }

    public String getLastDealDate() {
        return lastDealDate;
    }

    public void setLastDealDate(String lastDealDate) {
        this.lastDealDate = lastDealDate;
    }
    
    
    
    
    
}
