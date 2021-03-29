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
public class Customer extends Member{


    public Customer(long id, AccountIB aib, String name, 
            String email, String date,String address, String CMT,String phone) {
        super(id, aib, name, email, date,address,CMT,phone);
    }

    
    public Customer() {
    }

}
