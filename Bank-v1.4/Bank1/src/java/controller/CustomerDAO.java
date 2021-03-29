package controller;

import DAO.Check;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.BankNumber;
import model.Customer;

/**
 *
 * @author Admin
 */
public class CustomerDAO extends Check {

    //Lấy tên người sử dụng có tài khoản x
    public String getByUsername(String e) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT mb.name FROM accountib as aib , member as mb ";
        strSQL += " WHERE aib.id = mb.accountibid AND username ='" + e + "'";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(strSQL);
            Customer aib = null;
            while (rs.next()) {
                aib = new Customer();
                aib.setName(rs.getString(1));
            }
            return aib.getName();
        } catch (SQLException ex) {
            System.out.println("Eror: Customer get username" + ex);
        } finally {
            closeStatement(st);
            closeResult(rs);
            closeConnection(conn);
        }
        return null;
    }

    public long getIDCustormerByName(String nameaccount) {
        Connection conn = DB.getConnection();
        String strSQL = "Select bn.CustomerId from banknumber as bn, member as mb, accountib as aib , customer as ct "
                + " where mb.id = ct.memberid and aib.username = bn.NameBN and ct.memberid = bn.customerid "
                + " and  aib.username ='" + nameaccount + "' ";
        Statement st = null;
        ResultSet rs = null;
        BankNumber bn = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(strSQL);
            Customer aib = null;

            while (rs.next()) {
                aib = new Customer();
                bn = new BankNumber();
                aib.setId(rs.getLong("bn.CustomerId"));
                bn.setCustomer(aib);
            }
            return bn.getCustomer().getId();
        } catch (SQLException ex) {
            System.out.println("Eror: Customer get username" + ex);
        } finally {
            closeStatement(st);
            closeResult(rs);
            closeConnection(conn);
        }
        return bn.getCustomer().getId();
    }
}
