package controller;

import DAO.Check;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.BankNumber;
import model.Customer;
import model.Deal;
import model.InterestPayment;
import model.InterestRate2;
import model.Passbook;

import model.SaveMoney;
import model.TypePassbook2;
import model.Withdraw;

public class DealDAO extends Check {

    public boolean Add(SaveMoney sm) {
       
        boolean isThanhCong = false;
        Connection conn = DB.getConnection();
        Statement st = null;
        Statement st1 = null;
        try {
            st = conn.createStatement();
            st1 = conn.createStatement();
            String SQL = String.format("INSERT INTO savemoney(DealID, Deposits, DateDeposits) "
                    + "VALUES('%s','%s','%s')", sm.getId(), sm.getDeposits(), sm.getDateDeposits());
            isThanhCong = st.executeUpdate(SQL) > 0;
            if (isThanhCong) {
                String SQL1 = String.format("INSERT INTO passbook(ID, TypePassbookId, Name,OpenDateSB,DateExprice,status) "
                        + "VALUES('%s','%s','%s','%s','%s','%s')", sm.getPassbook().getId(), sm.getTp2().getId(), sm.getPassbook().getName(),
                        sm.getPassbook().getOpenDateSB(), sm.getPassbook().getDateExprice(), 1);
                isThanhCong = st.executeUpdate(SQL1) > 0;
                if (isThanhCong) {
                    System.out.println(sm.getBankNumber().getCurrentBalance() + "::::::" + sm.getBankNumber().getAvailableBalance());
                    String SQL2 = "UPDATE banknumber"
                            + " SET CurrentBalance ='" + sm.getBankNumber().getCurrentBalance() + "' "
                            + " , AvailableBalance ='" + sm.getBankNumber().getAvailableBalance() + "' "
                            + " Where nameBN ='" + sm.getBankNumber().getNameBN() + "'";
                    isThanhCong = st1.executeUpdate(SQL2) > 0;
                    if (isThanhCong) {
                        String SQL3 = String.format("INSERT INTO deal(ID, EmployeeId, PassbookId, TypePassbookId,"
                                + " InterestRateId, BankNumberId, InterestPaymentId)"
                                + " VALUES('%s','%s','%s','%s','%s','%s','%s')", sm.getId(), 1, sm.getPassbook().getId(), sm.getTp2().getId(),
                                sm.getIr().getId(), sm.getBankNumber().getId(), sm.getIp().getId());
                        System.out.println(sm.getBankNumber().getId());
                        isThanhCong = st1.executeUpdate(SQL3) > 0;
                    } else {
                        System.out.println("SQL3");
                    }
                } else {
                    System.out.println("SQL2");
                }
            } else {
                System.out.println("SQL1");
            }

        } catch (SQLException ex) {
            System.out.println("Error add Deal" + ex);
        } finally {
            closeConnection(conn);
            closeStatement(st);
            closeStatement(st1);
        }
        return isThanhCong;
    }

    public boolean WithdrawSB(Withdraw sm) {
        boolean isThanhCong = false;
        Connection conn = DB.getConnection();
        Statement st = null;
        Statement st1 = null;
        try {
            st = conn.createStatement();
            st1 = conn.createStatement();
            String SQL = String.format("INSERT INTO withdraw(DealID, DateWithdraw, InterestReceive) "
                    + "VALUES('%s','%s','%s')", sm.getId(), sm.getDateWithdraw(), sm.getInterestReceive());
            isThanhCong = st.executeUpdate(SQL) > 0;
            String SQL1 = "UPDATE banknumber"
                    + " SET CurrentBalance ='" + sm.getBankNumber().getCurrentBalance() + "' "
                    + " , AvailableBalance ='" + sm.getBankNumber().getAvailableBalance() + "' "
                    + " Where NameBN ='" + sm.getBankNumber().getNameBN() + "'";
            isThanhCong = st1.executeUpdate(SQL1) > 0;
            String SQL2 = String.format("INSERT INTO deal(ID, EmployeeId, PassbookId, TypePassbookId,"
                    + " InterestRateId, BankNumberId, InterestPaymentId)"
                    + " VALUES('%s','%s','%s','%s','%s','%s','%s')", sm.getId(), 1, sm.getPassbook().getId(), sm.getTp2().getId(),
                    sm.getIr().getId(), sm.getBankNumber().getId(), sm.getIp().getId());
            isThanhCong = st1.executeUpdate(SQL2) > 0;
            String SQL3 = "UPDATE passbook"
                    + " SET status ='" +sm.getPassbook().getStatus()+"' "
                    + " Where Id ='" + sm.getPassbook().getId()+ "'";
            isThanhCong = st1.executeUpdate(SQL3) > 0;

        } catch (SQLException ex) {
            System.out.println("Error add Deal with dr");
        } finally {
            closeConnection(conn);
            closeStatement(st);
            closeStatement(st1);
        }
        return isThanhCong;
    }

    public int getSizeDeal() {
        List<Deal> listAIB = new ArrayList();
        Connection conn = DB.getConnection();
        String strSQL = "SELECT * FROM deal";
        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            Deal aib = null;
            while (rs.next()) {
                aib = new Deal();
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

    public boolean isHasPassBookID(long dl) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            String query = "SELECT * FROM deal "
                    + " WHERE passbookid = '" + dl + "' ";
            return st.executeQuery(query).next();
        } catch (SQLException ex) {
            //Sổ tiết kiệm đã tồn tại
            System.out.println("Exits Passbook id");
        } finally {
            closeConnection(conn);
            closeStatement(st);
        }

        return false;
    }

    public List<SaveMoney> getPassbook(long customerid) {
        List<SaveMoney> listAIB = new ArrayList();
        Connection conn = DB.getConnection();
        String SQL = "SELECT dl.id, mb.name, pb.name, pb.OpenDateSB, tp.name,ir.Period, "
                + "ir.interestrate, bn.CurrentBalance, sm.Deposits, bn.nameBN, ip.typeip";
        SQL += " FROM deal as dl, passbook as pb, banknumber as bn, customer as ct, member as mb,"
                + " interestpayment as ip, interestrate as ir, typepassbook as tp, savemoney as sm";
        SQL += " where mb.id = ct.memberid and  bn.customerid = ct.memberid and bn.id = dl.banknumberid ";
        SQL += " and dl.passbookid = pb.id and dl.interestpaymentid = ip.id";
        SQL += " and dl.interestrateid = ir.id and pb.typepassbookid = tp.id";
        SQL += " and dl.typepassbookid=pb.typepassbookid and ir.typepassbookid = tp.id";
        SQL += " and dl.typepassbookid= ir.typepassbookid and sm.dealid = dl.id";
        SQL += " and dl.passbookid = pb.id and bn.customerid = '" + customerid + "' and pb.status ='1'";

        Statement comm = null;
        ResultSet rs = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(SQL);
            Deal dl = null;
            Passbook pb = null;
            TypePassbook2 tp = null;
            InterestRate2 ir = null;
            BankNumber bn = null;
            InterestPayment ip = null;
            SaveMoney sm = null;
            Customer ct = null;

            while (rs.next()) {
                dl = new Deal();
                dl.setId(rs.getLong(1));

                ct = new Customer();
                ct.setName(rs.getString(2));

                pb = new Passbook();
                pb.setName(rs.getString(3));
                pb.setOpenDateSB(rs.getString(4));
                dl.setPassbook(pb);

                tp = new TypePassbook2();
                tp.setTentk(rs.getString(5));
                dl.setTp2(tp);

                ir = new InterestRate2();
                ir.setKyHanGui(rs.getInt(6));
                ir.setLaiSuat(rs.getDouble(7));
                dl.setIr(ir);

                bn = new BankNumber();
                bn.setCurrentBalance(rs.getDouble(8));
                bn.setNameBN(rs.getString(10));
                bn.setCustomer(ct);
                dl.setBankNumber(bn);

                ip = new InterestPayment();
                ip.setTypeIP(rs.getString(11));
                dl.setIp(ip);

                sm = new SaveMoney();
                sm.setDeposits(rs.getDouble(9));
                sm.setDeal(dl);

                listAIB.add(sm);
            }
        } catch (SQLException ex) {
            System.out.println("Eror: save money get passbook" + ex);
        } finally {
            closeStatement(comm);
            closeResult(rs);
            closeConnection(conn);
        }
        return listAIB;
    }
    
    public long getIRByIdPass(long id) {
        Connection conn = DB.getConnection();
        String strSQL = "SELECT InterestRateId FROM deal where PassbookId ='" + id + "'";
        Statement comm = null;
        ResultSet rs = null;
        Deal pb = null;
        try {
            comm = conn.createStatement();
            rs = comm.executeQuery(strSQL);
            while (rs.next()) {
                pb = new Deal();
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
