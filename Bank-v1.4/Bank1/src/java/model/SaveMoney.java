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
public class SaveMoney extends Deal {

    protected Deal deal;
    protected double deposits;
    protected String dateDeposits;
    public SaveMoney(long id, Employee employee, String tern, Passbook passbook, TypePassbook2 tp2, BankNumber bankNumber, InterestPayment ip, InterestRate2 ir) {
        super(id, employee, tern, passbook, tp2, bankNumber, ip, ir);
    }

    public SaveMoney() {
    }

   
    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public double getDeposits() {
        return deposits;
    }

    public void setDeposits(double deposits) {
        this.deposits = deposits;
    }

    public String getDateDeposits() {
        return dateDeposits;
    }

    public void setDateDeposits(String dateDeposits) {
        this.dateDeposits = dateDeposits;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getTern() {
        return tern;
    }

    @Override
    public void setTern(String tern) {
        this.tern = tern;
    }

    @Override
    public Passbook getPassbook() {
        return passbook;
    }

    @Override
    public void setPassbook(Passbook passbook) {
        this.passbook = passbook;
    }

    @Override
    public BankNumber getBankNumber() {
        return bankNumber;
    }

    @Override
    public void setBankNumber(BankNumber bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Override
    public InterestPayment getIp() {
        return ip;
    }

    @Override
    public void setIp(InterestPayment ip) {
        this.ip = ip;
    }

}
