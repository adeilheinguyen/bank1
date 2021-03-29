/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author taint
 */
public class MemberDAO {
    public void updateMember(String email){

        Connection conn = null;
        Statement st = null;
        
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "UPDATE member SET AccountIBID = '"+0+"' WHERE email = '"+email+"'";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error update Member: " + ex);
    }
    }
}
