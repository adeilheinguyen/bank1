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
public class Deal {

    protected long id;
    protected Employee employee;
    protected String tern;
    protected Passbook passbook;
    protected TypePassbook2 tp2;
    protected BankNumber bankNumber;
    protected InterestPayment ip;
    protected InterestRate2 ir;

    public Deal(long id, Employee employee, String tern, Passbook passbook, TypePassbook2 tp2, BankNumber bankNumber, InterestPayment ip, InterestRate2 ir) {
        this.id = id;
        this.employee = employee;
        this.tern = tern;
        this.passbook = passbook;
        this.tp2 = tp2;
        this.bankNumber = bankNumber;
        this.ip = ip;
        this.ir = ir;
    }

    public TypePassbook2 getTp2() {
        return tp2;
    }

    public void setTp2(TypePassbook2 tp2) {
        this.tp2 = tp2;
    }

    public InterestRate2 getIr() {
        return ir;
    }

    public void setIr(InterestRate2 ir) {
        this.ir = ir;
    }

    public Deal() {
    }

    public InterestPayment getIp() {
        return ip;
    }

    public void setIp(InterestPayment ip) {
        this.ip = ip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTern() {
        return tern;
    }

    public void setTern(String tern) {
        this.tern = tern;
    }

    public Passbook getPassbook() {
        return passbook;
    }

    public void setPassbook(Passbook passbook) {
        this.passbook = passbook;
    }

    public BankNumber getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(BankNumber bankNumber) {
        this.bankNumber = bankNumber;
    }

}
