/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Dao;
import DAO.Check;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.AccountIB;

/**
 *
 * @author Admin
 */
public class AccountDAO extends Check implements Dao<AccountIB, String> {

    //Đồng bộ dữ liệu
    public static AccountDAO instance;

    public synchronized static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public boolean isHasUser(AccountIB user) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "SELECT * FROM accountib "
                    + " WHERE username = '" + user.getUsername() + "' "
                    + " AND password = '" + user.getPassword() + "' ";
            return st.executeQuery(query).next();
        } catch (SQLException ex) {
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }

        return false;
    }

    @Override
    public List<AccountIB> getAll() {
        List<AccountIB> listAIB = new ArrayList();
        Connection conn = DB.getConnection();
        String strSQL = "SELECT * FROM accountib";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            AccountIB aib;
            while (rs.next()) {
                aib = new AccountIB();
                aib.setId(rs.getLong(1));
                aib.setUsername(rs.getString(2));
                aib.setPassword(rs.getString(3));

                listAIB.add(aib);
            }
        } catch (SQLException ex) {
            System.out.println("Eror: Account get ALL" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return listAIB;
    }

    @Override
    public AccountIB getByID(String e) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT * FROM accountib where username ='" + e + "'";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(strSQL);
            AccountIB aib = null;
            while (rs.next()) {
                aib = new AccountIB();
                aib.setId(rs.getLong(1));
                aib.setUsername(rs.getString(2));
                aib.setPassword(rs.getString(3));
            }
            return aib;
        } catch (SQLException ex) {
            System.out.println("Eror: Account get username" + ex);
        } finally {
            closeStatement(st);
            closeResult(rs);
            closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean Add(AccountIB t) {
        boolean isTC2 = false;
        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String SQL = String.format("INSERT INTO accountib(ID,Username,Password,Status) "
                    + "VALUES('%s','%s','%s','%s')", t.getId(), t.getUsername(), t.getPassword(), 1);
            isTC2 = st.executeUpdate(SQL) > 0;
            String query = "UPDATE member as mb, banknumber as bn,customer as ct, accountib as aib "
                    + " SET mb.AccountIBID = '" + t.getId() + "' ";
            query += " WHERE mb.id = ct.Memberid AND bn.CustomerId = mb.id "
                    + "AND bn.NameBN = aib.Username AND bn.CustomerId = ct.Memberid "
                    + " AND bn.CustomerId = mb.ID AND aib.Username ='" + t.getUsername() + "'";
            isTC2 = st.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            System.out.println("Error ADD AccountIB: " + ex);
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }
        return isTC2;
    }

    @Override
    public boolean Delete(String t) {
        boolean isSuccess = false;
        Connection conn = DB.getConnection();
        PreparedStatement prs = null;
        String SQL = "DELETE FROM accountib WHERE "
                + " username = ? ";
        try {
            prs = conn.prepareStatement(SQL);
            prs.setString(1, t);
            isSuccess = prs.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error Delete Account");
        } finally {
            closePre(prs);
        }
        return isSuccess;
    }

    public boolean Edit(AccountIB t) {
        Connection conn = null;
        Statement st = null;
        boolean isTC = false;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "UPDATE accountib "
                    + " SET username = '" + t.getUsername() + "', "
                    + "  password = '" + t.getPassword() + "' ";
            query += " WHERE id = '" + t.getId() + "'";
            isTC = st.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            System.out.println("Error Update AccountIB: " + ex);
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }
        return isTC;
    }

    //Kiểm tra thông tin tài khoản đã có tài khoản ib chưa
    public boolean checkLogin(AccountIB aib) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "SELECT * FROM accountib "
                    + " WHERE username = '" + aib.getUsername() + "' "
                    + " AND password = '" + aib.getPassword() + "' AND status = 1";
            return st.executeQuery(query).next();
        } catch (SQLException ex) {
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }
        return false;
    }

    public boolean logOut(AccountIB t) {
        Connection conn = null;
        Statement st = null;
        boolean isTC = false;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "UPDATE accountib "
                    + " SET status = 0";
            query += " WHERE username = '" + t.getId() + "'";
            isTC = st.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            System.out.println("Error Update AccountIB: " + ex);
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }
        return isTC;
    }

    public int getSizeAccount() {
        List<AccountIB> listAIB = new ArrayList();
        Connection conn = DB.getConnection();
        String strSQL = "SELECT * FROM accountib";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            AccountIB aib = null;
            while (rs.next()) {
                aib = new AccountIB();
                aib.setId(rs.getLong(1));
                listAIB.add(aib);
            }
        } catch (SQLException ex) {
            System.out.println("Eror: SaveMoney get size" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return listAIB.size();
    }

    
}
