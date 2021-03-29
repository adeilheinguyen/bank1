/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Check;
import DAO.Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.BankNumber;
import model.Passbook;

/**
 *
 * @author Admin
 */
public class PassbookDAO extends Check implements Dao<Passbook, String> {

    //Đồng bộ dữ liệu
    public static AccountDAO instance;

    public synchronized static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    @Override
    public List<Passbook> getAll() {
        List<Passbook> listAIB = new ArrayList();

        return listAIB;
    }

    @Override
    public Passbook getByID(String e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Add(Passbook t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getsize() {
        List<Passbook> listAIB = new ArrayList();
        Connection conn = DB.getConnection();
        String strSQL = "SELECT * FROM passbook";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            Passbook aib;
            while (rs.next()) {
                aib = new Passbook();
                aib.setId(rs.getLong(1));
                aib.setName(rs.getString(2));
                aib.setOpenDateSB(rs.getString(3));

                listAIB.add(aib);
            }
        } catch (SQLException ex) {
            System.out.println("Eror: passbook get ALL" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return listAIB.size();
    }

    public Passbook getAllSBByName(String name) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT * FROM passbook where Name ='" + name + "'";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            Passbook pb = null;
            while (rs.next()) {
                pb = new Passbook();
                pb.setId(rs.getLong(1));
                pb.setName(rs.getString(2));
                pb.setOpenDateSB(rs.getString(3));
                pb.setDateExprice(rs.getString(4));
                pb.setStatus(rs.getInt(5));
            }
            return pb;
        } catch (SQLException ex) {
            System.out.println("Eror: passbook get getAllSBByName" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return null;
    }

    public String getDateExpById(long id) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT DateExprice FROM passbook where Id ='" + id + "'";
        Statement comm = null;
        ResultSet rs = null;
        Passbook pb = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);

            while (rs.next()) {
                pb = new Passbook();
                pb.setDateExprice(rs.getString(1));
            }
            return pb.getDateExprice();
        } catch (SQLException ex) {
            System.out.println("Eror: passbook get getAllSBByName" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return pb.getDateExprice();
    }
    
    public long getIdByName(String name) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT id FROM passbook where Name ='" + name + "'";
        Statement comm = null;
        ResultSet rs = null;
        Passbook pb = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            while (rs.next()) {
                pb = new Passbook();
                pb.setId(rs.getLong(1));
            }
            return pb.getId();
        } catch (SQLException ex) {
            System.out.println("Eror: passbook get getAllSBByName" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return pb.getId();
    }
    
    public long getIdBNByPassbookId(long id) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT BankNumberId FROM deal where PassbookId ='" + id + "'";
        Statement comm = null;
        ResultSet rs = null;
        BankNumber pb = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            while (rs.next()) {
                pb = new BankNumber();
                pb.setId(rs.getLong(1));
            }
            return pb.getId();
        } catch (SQLException ex) {
            System.out.println("Eror: passbook get getAllSBByName" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return pb.getId();
    }

}
