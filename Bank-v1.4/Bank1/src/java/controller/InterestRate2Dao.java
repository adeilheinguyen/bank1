/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Check;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.InterestRate2;


public class InterestRate2Dao extends Check{
     public static InterestRate2Dao instance;

    public synchronized static InterestRate2Dao getInstance() {
        if (instance == null) {
            instance = new InterestRate2Dao();
        }
        return instance;
    }
    
    
    
    public double getLaiSuat(int typepassbookid, int kyhangui, String soTienGui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + typepassbookid + "' and period='"+kyhangui+"' and money='"+soTienGui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    
    public long getID(int typepassbookid, int kyhangui, String soTienGui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT id FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + typepassbookid + "' and period='"+kyhangui+"' and money='"+soTienGui+"'";
        Statement comm = null;
        ResultSet rs = null;
        long ls= 0;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setId(rs.getLong("id"));
            }
            ls = ir2.getId();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    
    public String getMoney(int typepassbookid, int kyhangui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT money FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + typepassbookid + "' and period='"+kyhangui+"'";
        Statement comm = null;
        ResultSet rs = null;
        String ls= "";
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setSoTienGui(rs.getString("money"));
            }
            ls = ir2.getSoTienGui();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }

    
    public double getLaiSuatTKcoKyHan(int kyhangui, String soTienGui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 1 + "' and period='"+kyhangui+"' and money='"+soTienGui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    
    public double getLaiSuatKyHanThuong() {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 2 + "' and period='"+0+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    
    public double getLaiSuatSuperKid(String soTienGui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 3 + "' and period='"+12+"' and money='"+soTienGui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    public double getLaiSuatPLOL(int kyhangui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 4 + "' and period='"+kyhangui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    public double getLaiSuatTaiTamTaiHienCBH(int kyhangui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 5 + "' and period='"+kyhangui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    public double getLaiSuatTaiTamTaiHienKBH(int kyhangui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 6 + "' and period='"+kyhangui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    
    public double getLaiSuatAnLoc(int kyhangui, String soTienGui) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT interestrate FROM interestrate ";
        strSQL += " WHERE typepassbookid='" + 7 + "' and period='"+kyhangui+"' and money='"+soTienGui+"'";
        Statement comm = null;
        ResultSet rs = null;
        double ls= 0d;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            InterestRate2 ir2 = null;
            while (rs.next()) {
                ir2 = new InterestRate2();
                ir2.setLaiSuat(rs.getDouble("interestrate"));
            }
            ls = ir2.getLaiSuat();
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return ls;
    }
    
    
}
