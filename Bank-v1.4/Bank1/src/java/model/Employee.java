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
public class Employee extends Member{
    private double salary;

    public Employee(double salary) {
        this.salary = salary;
    }

    public Employee(double salary, long id, AccountIB aib, 
            String name, String email, String date,String address, String CMT,String phone) {
        super(id, aib, name, email, date,address,CMT,phone);
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(long id, AccountIB aib, String name, String email, String date,String address, String CMT,String phone) {
        super(id, aib, name, email, date,address, CMT,phone);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
