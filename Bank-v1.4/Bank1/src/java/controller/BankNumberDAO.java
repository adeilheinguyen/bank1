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
import model.Customer;

/**
 *
 * @author Admin
 */
public class BankNumberDAO extends Check implements Dao<BankNumber, String> {

    public static BankNumberDAO instance;

    public synchronized static BankNumberDAO getInstance() {
        if (instance == null) {
            instance = new BankNumberDAO();
        }
        return instance;
    }

    public boolean isHasEmailNameAcc(String nameAcc, String email) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "Select mb.Email, bn.NameBN from customer as ct, member as mb,"
                    + " banknumber as bn  where mb.ID = ct.MemberID and ct.MemberID = bn.CustomerId"
                    + " and mb.Email = '" + email + "' "
                    + " and bn.NameBN = '" + nameAcc + "'";
            return st.executeQuery(query).next();
        } catch (SQLException ex) {
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }

        return false;
    }

    public int isHasAccount(String nameAcc, String email) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        int idAccount = 0;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "Select mb.AccountIBID from customer as ct, member as mb,"
                    + " banknumber as bn  where mb.ID = ct.MemberId and ct.MemberId = bn.CustomerId"
                    + " and mb.Email = '" + email
                    + "' and bn.NameBN = '" + nameAcc + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                idAccount = rs.getInt("mb.Accountibid");
            }

        } catch (SQLException ex) {
        } finally {
            closeConnection(conn);
            closeStatement(st);
            closeResult(rs);
        }

        return idAccount;
    }

    @Override
    public List<BankNumber> getAll() {
        List<BankNumber> listBN = new ArrayList();
        Connection conn = DB.getConnection();
        String strSQL = "SELECT bn.*, mb.name FROM customer as ct, banknumber as bn, member as mb,accountib as aib ";
        strSQL += " WHERE ct.memberid = bn.customerid and bn.customerid = mb.id AND aib.id = mb.id";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            BankNumber bn;
            Customer ct;
            while (rs.next()) {
                bn = new BankNumber();
                bn.setId(rs.getLong(1));
                ct = new Customer();
                ct.setId(rs.getLong(2));
                bn.setNameBN(rs.getString(3));
                bn.setCurrentBalance(rs.getDouble(4));
                bn.setAvailableBalance(rs.getDouble(5));
                bn.setOpenDateAccount(rs.getString(6));
                bn.setLastDealDate(rs.getString(7));
                ct.setName(rs.getString(8));
                bn.setCustomer(ct);

                listBN.add(bn);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return listBN;
    }

    public List<BankNumber> getAll(long idCus) {
        List<BankNumber> listBN = new ArrayList();
        Connection conn = DB.getConnection();
        String strSQL = "SELECT bn.*, mb.name FROM customer as ct, banknumber as bn, member as mb,accountib as aib ";
        strSQL += " WHERE ct.memberid = bn.customerid and bn.customerid = mb.id AND aib.id = mb.id AND bn.customerid='" + idCus + "'";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            BankNumber bn;
            Customer ct;
            while (rs.next()) {
                bn = new BankNumber();
                bn.setId(rs.getLong(1));
                ct = new Customer();
                ct.setId(rs.getLong(2));
                bn.setNameBN(rs.getString(3));
                bn.setCurrentBalance(rs.getDouble(4));
                bn.setAvailableBalance(rs.getDouble(5));
                bn.setOpenDateAccount(rs.getString(6));
                bn.setLastDealDate(rs.getString(7));
                ct.setName(rs.getString(8));
                bn.setCustomer(ct);

                listBN.add(bn);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return listBN;
    }

    public BankNumber getName(String nameBN) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT bn.*, mb.name FROM customer as ct, banknumber as bn, member as mb,accountib as aib ";
        strSQL += " WHERE ct.memberid = bn.customerid and bn.customerid = mb.id AND aib.id = mb.id  ";
        strSQL += " AND bn.namebn ='" + nameBN + "'";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            BankNumber bn = null;
            Customer ct;
            while (rs.next()) {
                bn = new BankNumber();
                bn.setId(rs.getLong(1));
                ct = new Customer();
                ct.setId(rs.getLong(2));
                bn.setNameBN(rs.getString(3));
                bn.setCurrentBalance(rs.getDouble(4));
                bn.setAvailableBalance(rs.getDouble(5));
                bn.setOpenDateAccount(rs.getString(6));
                bn.setLastDealDate(rs.getString(7));
                ct.setName(rs.getString(8));
                bn.setCustomer(ct);
            }
            return bn;
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return null;
    }

    @Override
    public BankNumber getByID(String id) {
        return null;
    }

    @Override
    public boolean Add(BankNumber bn) {
        boolean isThanhCong = false;
        return isThanhCong;
    }

    @Override
    public boolean Delete(String mabn) {
        boolean isSuccess = false;
        return isSuccess;

    }

    public long getID(String nameBank) {
        long id = 0;
        Connection conn = DB.getConnection();
        String strSQL = "SELECT id FROM bank1.banknumber where NameBN = '" + nameBank + "'";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            BankNumber bn = null;
            while (rs.next()) {
               id = rs.getLong("id");
            }
            return id;
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return id;
    }
    
    public double getCurrentMoney(String nameBank) {
        double id = 0d;
        Connection conn = DB.getConnection();
        String strSQL = "SELECT AvailableBalance FROM bank1.banknumber where NameBN = '" + nameBank + "'";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            BankNumber bn = null;
            while (rs.next()) {
               id = rs.getDouble("AvailableBalance");
            }
            System.out.println("So tien trong tai khoan la : "+id);
            return id;
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return id;
    }

}
