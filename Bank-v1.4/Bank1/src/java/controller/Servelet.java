/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountIB;
import model.BankNumber;
import model.InterestPayment;
import model.InterestRate2;
import model.Passbook;
import model.SaveMoney;
import model.TypePassbook2;
import model.Withdraw;

/**
 *
 * @author Admin
 */
public class Servelet {

    public static Servelet instance;

    public synchronized static Servelet getInstance() {
        if (instance == null) {
            instance = new Servelet();
        }
        return instance;
    }

    public String checkConfirmIR(String nameAcc, String emailfrom, int number) {
        if (nameAcc == " " || emailfrom == " " || nameAcc == null || emailfrom == null) {
            return "Get code fail";
        } else {
            SendDAO sd = new SendDAO();
            BankNumberDAO bn = new BankNumberDAO();
            boolean checkEmailNameAcc = bn.isHasEmailNameAcc(nameAcc, emailfrom);
            int checkHasAccount = bn.isHasAccount(nameAcc, emailfrom);
//            System.out.println(checkEmailNameAcc+"-"+checkHasAccount);
//            System.out.println(checkEmailNameAcc && checkHasAccount == 0);
        if (checkEmailNameAcc){
            if (checkHasAccount == 0) {
                boolean isCheckSend = sd.send(emailfrom, number);
                return "Success";
            } else {
                return "Get code fail because account already exist";
            }
          }else return "Email is not register yet";
        }
    }

    public String registerIB(String nameAcc, String pass, String idxn, String email, String check) {
        
//        System.out.println("idxn"+idxn);
//        System.out.println("idcheck"+check);
        if (pass.length() < 8) {
            return "Pass no less than 8 characters";
        } else {
            AccountDAO ad = new AccountDAO();
            AccountIB aib = new AccountIB(ad.getSizeAccount() + 1, nameAcc, pass);
            if (!ad.isHasUser(aib)) {
                if (check.equals(idxn)) {
                    if (ad.Add(aib)) {
                        return "Success";
                    } else {
                        return "Register fail";
                    }
                } else {
                    return "Code is incorrect";
                }
            } else {
                return "Account already exist";
            }
              
        }
       
    }

    public String loginPage(String nameaccount, String password) {
        if (nameaccount.equals(" ") || password.equals(" ") || nameaccount.equals("null") || password.equals("null")||
                nameaccount == null|| password == null || nameaccount.isEmpty() || nameaccount.length()==0
                ||  password.isEmpty() || password.length()==0) {
            return "Field is not empty";
        } else {
            AccountIB aib = new AccountIB(nameaccount, password);
            AccountDAO ad = new AccountDAO();
            CustomerDAO cd = new CustomerDAO();
            boolean check = ad.checkLogin(aib);
            if (check == true) {
                String ct = cd.getByUsername(nameaccount);
                long idCus = cd.getIDCustormerByName(nameaccount);
               if (ct == null || idCus == 0) {
                    return "Account doesn't exist";
                } else {
                    return "Success";
                }
            } else {
                return "Login fail";
            }

        }
    }

    public String openSB(String taiKhoan, String loaiHTGTK, String khg, String soTienGui, String sdht, String hinhThuTraLai) {
        
//        if(Double.parseDouble(soTienGui) <= 0 ) return "Money can't less 0vnd";
        
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(12);
        DealDAO dd = new DealDAO();
        SaveMoney sm = new SaveMoney();
        long idsm = dd.getSizeDeal() + 1;
        
        BankNumber bankNumber;
//        bankNumber.setId(taiKhoan);
//        sm.setBankNumber(bankNumber);
        sm.setId(idsm);
        sm.setDeposits(Double.parseDouble(soTienGui));
        sm.setDateDeposits(timeStamp);
        // Loại hình thức gửi tiết kiệm ....
        TypePassbook2 tp2 = new TypePassbook2();
        tp2.setId(Integer.parseInt(loaiHTGTK));
        tp2.setTentk(loaiHTGTK);
        sm.setTp2(tp2);

        //Thông tin sổ tiết kiệm
        PassbookDAO pbd = new PassbookDAO();
        Passbook pb = new Passbook();
        long idpbd = pbd.getsize() + 1;
        pb.setId(idpbd);
        pb.setTp2(tp2);
        pb.setName("STK" + idpbd + "." + taiKhoan);
        pb.setOpenDateSB(timeStamp);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, Integer.parseInt(khg));
        pb.setDateExprice(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        sm.setPassbook(pb);

        //Lãi suất gửi tiết kiệm
        InterestRate2Dao ird = new InterestRate2Dao();
        InterestRate2 ir = new InterestRate2();
        ir.setTp2(tp2);
        ir.setKyHanGui(Integer.parseInt(khg));
        ir.setSoTienGui(ird.getMoney(Integer.parseInt(loaiHTGTK), Integer.parseInt(khg)));
        ir.setLaiSuat(ird.getLaiSuat(Integer.parseInt(loaiHTGTK), Integer.parseInt(khg), ir.getSoTienGui()));
        ir.setId(ird.getID(Integer.parseInt(loaiHTGTK), Integer.parseInt(khg), ir.getSoTienGui()));
        sm.setIr(ir);
    
        //Thông tin tài khoản ngân hàng
        BankNumberDAO bnd = new BankNumberDAO();
        BankNumber bn = new BankNumber();
        bn.setNameBN(taiKhoan);
        bn.setId(bnd.getID(bn.getNameBN()));
//        if (soTienGui.equals(" ") || soTienGui.isEmpty() || soTienGui == null) {
//            return "Please enter number of money";
//        } else {
            if (Double.parseDouble(soTienGui) < 3000000d) {
                return "money no less than 3000000vnd";
            } else {
                double cc = bnd.getCurrentMoney(bn.getNameBN()) - Double.parseDouble(soTienGui);
                if (cc < 0d) {
                    return "money cannot more than your currentBalance";
                } else {
                    bn.setCurrentBalance(cc);
                    bn.setAvailableBalance(cc);
                    sm.setBankNumber(bn);

                    //Thông tin phương thức trả lãi: lãi nhập gốc or chuyển khoản
                    InterestPayment ip = new InterestPayment();
                    if (hinhThuTraLai.equals("nhapgoc")) {
                        ip.setId(1);
                    } else {
                        ip.setId(2);
                    }
                    sm.setIp(ip);
                    if (dd.Add(sm)) {
                            return "Success";
                        } else {
                            return "Fail";
                        }
//                    if (!dd.isHasPassBookID(pb.getId())) {
//                        if (dd.Add(sm)) {
//                            return "Open Successul";
//                        } else {
//                            return "Open fail";
//                        }
//                    } else {
//                        return "PassBook exist!";
//                    }
                }
            }
//        }
    }

    public String withDraw(String stk, String tenChuSo, String tklk, String sdht, String stgtk, String loaitk, String kyhan,
            String httl, String ngayms, String laisuat, String ngaytt) {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        double d_sdht = Double.parseDouble(sdht);
        double d_sdhtbd = d_sdht;
        double d_stgtk = Double.parseDouble(stgtk);
        double d_stgtkbd = d_stgtk;
        int d_kyhan = Integer.parseInt(kyhan);
        String ngayRutSo = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(Calendar.getInstance().getTime());
        double d_laisuat = Double.parseDouble(laisuat);
        int d_ngaytt = Integer.parseInt(ngaytt);
        DealDAO dd = new DealDAO();
        Withdraw wd = new Withdraw();
        TypePassbook2 tp = new TypePassbook2();
        Passbook pb = new Passbook();
        PassbookDAO pbd = new PassbookDAO();
        BankNumber bn = new BankNumber();
        InterestPayment ip = new InterestPayment();
        InterestRate2 ir = new InterestRate2();
        InterestRate2Dao ird = new InterestRate2Dao();

        long idsm = dd.getSizeDeal() + 1;
        wd.setId(idsm);
        wd.setDateWithdraw(ngayRutSo);
        if (httl.equals("Nhap goc")) {
            ip.setId(1);

        } else {
            ip.setId(2);
        }
        wd.setIp(ip);

        tp.setId(1);
        wd.setTp2(tp);
        long idPassbook = pbd.getIdByName(stk);
        pb.setId(idPassbook);
        pb.setStatus(0);
        wd.setPassbook(pb);

        long idIR = dd.getIRByIdPass(idPassbook);
        ir.setId(idIR);
        wd.setIr(ir);

        String dateEXP = pbd.getDateExpById(pbd.getIdByName(stk));
        System.out.println(ngayRutSo);
        System.out.println(dateEXP);
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").parse(ngayRutSo);
        } catch (ParseException ex) {
            Logger.getLogger(Servelet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date date2 = null;
        try {
            date2 = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").parse(dateEXP);
        } catch (ParseException ex) {
            Logger.getLogger(Servelet.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("date1 = "+date1);
//        System.out.println("date2 = "+date2);
        //Nếu ngày rút sổ trước  
        if (date1.before(date2)) {
            //Nhap goc
            if (ip.getId() == 1) {
                double d_LaiSuat = ird.getLaiSuatKyHanThuong();
                double d_tienlainhandc = (double) (d_stgtk * d_ngaytt * 30 * d_LaiSuat) / 36000;
                d_sdht += d_stgtk;
                d_sdht += d_tienlainhandc;
            } else {
                double d_LaiSuat = ird.getLaiSuatKyHanThuong();
                double d_tienlainhandc = (double) (d_stgtk * d_ngaytt * 30 * d_LaiSuat) / 36000;
                
                // ??????????
                d_sdht += d_stgtk;
                // ??????
                d_sdht += d_tienlainhandc;
            }
        } else if (date1.after(date2)) {
            //Nếu ngày rút sổ sau 
            int ngayRTT = (int) d_ngaytt / 30;
            int sodu = d_ngaytt - ngayRTT * 30;
            d_sdht += (d_sdht * ird.getLaiSuatKyHanThuong() * sodu) / 36000;
            if (ip.getId() == 1) { //nhapgoc
                //       double d_tienLai = (double) (d_sdht * d_kyhan * 30 * d_laisuat) / 36000;
                for (int i = 0; i < sodu; i++) {
                    
                    d_sdht += d_stgtk;
                    
                    d_sdht += (d_sdht * d_kyhan * 30 * d_laisuat) / 36000;
                }
            } else { //chuyenkhoan
                double d_tienLai = (double) (d_sdhtbd * d_kyhan * 30 * d_laisuat) / 36000;
                d_sdht += d_stgtk;
                d_tienLai += d_tienLai * sodu;
                d_sdht += d_tienLai;
            }

        } else {
            //Rút đúng hạn
            double d_LaiSuat = d_laisuat;
            if (ip.getId() == 1) { //nhap goc
                double d_tienlainhandc = (double) (d_stgtk * d_kyhan * 30 * d_LaiSuat) / 36000;
                d_sdht += d_stgtk;
                d_sdht += d_tienlainhandc;
            } else { //chuyenkhoan
                //So tien              
                double d_tienlainhandc = (double) (d_stgtkbd * d_kyhan * 30 * d_LaiSuat) / 36000;
                d_sdht += d_stgtk;
                d_sdht += d_tienlainhandc;
            }
        }
        bn.setId(pbd.getIdBNByPassbookId(idPassbook));
        bn.setNameBN(tklk);
        bn.setCurrentBalance(d_sdht);
        bn.setAvailableBalance(d_sdht);
        wd.setBankNumber(bn);
        wd.setInterestReceive(Double.parseDouble(df.format(d_sdht - d_sdhtbd)));

        if (dd.WithdrawSB(wd)) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    public String inRate(String soTien,String loaiTK,String kyhangui,String kyhantinhlai,
           String chooseTinhLai,String htTraLai) {
        String errMessageSoTien = "Please insert the number of money";
        if (soTien.isEmpty() || soTien.equals("") || soTien == null || soTien.length() == 0) {
           return errMessageSoTien;
        } else {
            double d_soTien = Double.parseDouble(soTien);
            double d_soTienBD = d_soTien;
            int d_kyhangui = Integer.parseInt(kyhangui);
            String errMessageKHTL = "Please insert the date";
            if (kyhantinhlai.equals("") || kyhantinhlai.isEmpty()) {
                return errMessageKHTL;
            } else {
                int d_kyhantinhlai = Integer.parseInt(kyhantinhlai);

                double tienLai = 0d;

                InterestRate2Dao ir2Dao = new InterestRate2Dao();
                String strLaiSuat = "";
                double d_LaiSuat = 0d;
                String soTienGui = "";

                //So tien min va max ung voi cac cot moc gui tien
                double min = 3000000d;
                double mon1 = 1000000000d;
                double mon2 = 3000000000d;

                if (loaiTK.equals("1")) {
                    if (kyhangui.equals("1") || kyhangui.equals("3") || kyhangui.equals("6")
                            || kyhangui.equals("9") || kyhangui.equals("12") || kyhangui.equals("24") || kyhangui.equals("36")) {
                        if (chooseTinhLai.equals("thang")) {
                            //Ki?m tra s? ti?n g?i
                            if (d_soTien < 1000000000) {
                                soTienGui = "< 1 ty";
                            } else if (d_soTien >= mon1 && d_soTien < mon2) {
                                soTienGui = "1 ty - 3 ty";
                            } else {
                                soTienGui = "> 3 ty";
                            }

                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlai - d_kyhangui;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        } else {
                            if (d_soTien < 1000000000) {
                                soTienGui = "< 1 ty";
                            } else if (d_soTien >= mon1 && d_soTien < mon2) {
                                soTienGui = "1 ty - 3 ty";
                            } else {
                                soTienGui = "> 3 ty";
                            }

                            int d_kyhantinhlais = (int) d_kyhantinhlai / 30;
                            int sodu = d_kyhantinhlai - d_kyhantinhlais * 30;
                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlais - d_kyhangui;
                            //So tien
                            d_soTien += (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * sodu) / 36000;
                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTKcoKyHan(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        }
                    }
                } else if (loaiTK.equals("2")) {
                    if (kyhangui.equals("0")) {
                        if (chooseTinhLai.equals("thang")) {
                            d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                            tienLai = (double) (d_soTien * d_kyhantinhlai * 30 * d_LaiSuat) / 36000;
                            d_soTien += tienLai;
                        } else {
                            d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                            tienLai = (double) (d_soTien * d_kyhantinhlai * d_LaiSuat) / 36000;
                            d_soTien += tienLai;
                        }
                    }

                } else if (loaiTK.equals("3")) {
                    if (kyhangui.equals("12")) {
                        if (chooseTinhLai.equals("thang")) {
                            double monx1 = 100000000;
                            double monx2 = 200000000;
                            double monx3 = 400000000;
                            double monx4 = 600000000;
                            double monx5 = 800000000;

                            //Ki?m tra s? ti?n g?i
                            if (d_soTien < monx1) {
                                soTienGui = "< 100 trieu";
                            } else if (d_soTien >= monx1 && d_soTien < monx2) {
                                soTienGui = "100-200trieu";
                            } else if (d_soTien >= monx2 && d_soTien < monx3) {
                                soTienGui = "200-400tr";
                            } else if (d_soTien >= monx3 && d_soTien < monx4) {
                                soTienGui = "400-600tr";
                            } else if (d_soTien >= monx4 && d_soTien < monx5) {
                                soTienGui = "600-800tr";
                            } else {
                                soTienGui = "> 800";
                            }

                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlai - d_kyhangui;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        } else {
                            //Ngay
                            double monx1 = 100000000;
                            double monx2 = 200000000;
                            double monx3 = 400000000;
                            double monx4 = 600000000;
                            double monx5 = 800000000;

                            //Ki?m tra s? ti?n g?i
                            if (d_soTien < monx1) {
                                soTienGui = "< 100 trieu";
                            } else if (d_soTien >= monx1 && d_soTien < monx2) {
                                soTienGui = "100-200trieu";
                            } else if (d_soTien >= monx2 && d_soTien < monx3) {
                                soTienGui = "200-400tr";
                            } else if (d_soTien >= monx3 && d_soTien < monx4) {
                                soTienGui = "400-600tr";
                            } else if (d_soTien >= monx4 && d_soTien < monx5) {
                                soTienGui = "600-800tr";
                            } else {
                                soTienGui = "> 800";
                            }

                            int d_kyhantinhlais = (int) d_kyhantinhlai / 30;
                            int sodu = d_kyhantinhlai - d_kyhantinhlais * 30;
                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlais - d_kyhangui;
                            //So tien
                            d_soTien += (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * sodu) / 36000;
                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatSuperKid(soTienGui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        }
                    }
                } else if (loaiTK.equals("4")) {
                    if (kyhangui.equals("1") || kyhangui.equals("3") || kyhangui.equals("6")
                            || kyhangui.equals("9") || kyhangui.equals("12") || kyhangui.equals("24") || kyhangui.equals("36")) {
                        if (chooseTinhLai.equals("thang")) {
                            int ngayThucTe = d_kyhantinhlai - d_kyhangui;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        } else {
                            //Ngày
                            int d_kyhantinhlais = (int) d_kyhantinhlai / 30;
                            int sodu = d_kyhantinhlai - d_kyhantinhlais * 30;
                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlais - d_kyhangui;
                            //So tien
                            d_soTien += (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * sodu) / 36000;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatPLOL(d_kyhangui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }

                        }
                    }
                } else if (loaiTK.equals("5")) {
                    if (kyhangui.equals("1") || kyhangui.equals("3") || kyhangui.equals("6")
                            || kyhangui.equals("9") || kyhangui.equals("12") || kyhangui.equals("24") || kyhangui.equals("36")) {
                        if (chooseTinhLai.equals("thang")) {
                            int ngayThucTe = d_kyhantinhlai - d_kyhangui;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        } else {
                            //Ngày
                            int d_kyhantinhlais = (int) d_kyhantinhlai / 30;
                            int sodu = d_kyhantinhlai - d_kyhantinhlais * 30;
                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlais - d_kyhangui;
                            //So tien
                            d_soTien += (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * sodu) / 36000;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienCBH(d_kyhangui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        }
                    }
                } else if (loaiTK.equals("6")) {
                    if (kyhangui.equals("1") || kyhangui.equals("3") || kyhangui.equals("6")
                            || kyhangui.equals("9") || kyhangui.equals("12") || kyhangui.equals("24") || kyhangui.equals("36")) {
                        if (chooseTinhLai.equals("thang")) {
                            int ngayThucTe = d_kyhantinhlai - d_kyhangui;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        } else {
                            //Ngày
                            int d_kyhantinhlais = (int) d_kyhantinhlai / 30;
                            int sodu = d_kyhantinhlai - d_kyhantinhlais * 30;
                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlais - d_kyhangui;
                            //So tien
                            d_soTien += (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * sodu) / 36000;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatTaiTamTaiHienKBH(d_kyhangui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }

                        }
                    }
                } else if (loaiTK.equals("7")) {
                    if(d_soTien < 25000000){
                        return "Vui lòng gửi số tiền lớn hơn 25.000.000 VND ";
                    }
                    if (kyhangui.equals("3") || kyhangui.equals("6")
                            || kyhangui.equals("9") || kyhangui.equals("12")) {
                        if (chooseTinhLai.equals("thang")) {
                            double monx1 = 25000000;
                            double monx2 = 50000000;
                            double monx3 = 200000000;
                            double monx4 = 300000000;

                            //Ki?m tra s? ti?n g?i
                            if (d_soTien >= monx1 && d_soTien < monx2) {
                                soTienGui = "25-50tr";
                            } else if (d_soTien >= monx2 && d_soTien < monx3) {
                                soTienGui = "50-200tr";
                            } else if (d_soTien >= monx3 && d_soTien < monx4) {
                                soTienGui = "200-300tr";
                            } else {
                                soTienGui = "> 300";
                            }

                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlai - d_kyhangui;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlai;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlai - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlai / d_kyhangui;

                                    int tt = d_kyhantinhlai - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        } else {
                            //Ngay
                            double monx1 = 25000000;
                            double monx2 = 50000000;
                            double monx3 = 200000000;
                            double monx4 = 300000000;

                            //Ki?m tra s? ti?n g?i
                            if (d_soTien >= monx1 && d_soTien < monx2) {
                                soTienGui = "25-50tr";
                            } else if (d_soTien >= monx2 && d_soTien < monx3) {
                                soTienGui = "50-200tr";
                            } else if (d_soTien >= monx3 && d_soTien < monx4) {
                                soTienGui = "200-300tr";
                            } else {
                                soTienGui = "> 300";
                            }

                            //Tính s? ngày th?c t? g?i
                            int d_kyhantinhlais = (int) d_kyhantinhlai / 30;
                            int sodu = d_kyhantinhlai - d_kyhantinhlais * 30;
                            //Tính s? ngày th?c t? g?i
                            int ngayThucTe = d_kyhantinhlais - d_kyhangui;
                            //So tien
                            d_soTien += (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * sodu) / 36000;

                            //S? ngày < 0 : rút lãi tr??c th?i h?n, >=0 rút lãi ?úng ho?c sau th?i h?n
                            //Ki?m tra hình th?c tr? lãi
                            if (htTraLai.equals("nhapgoc")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;

                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                    } else {
                                        for (int i = 0; i < thuong; i++) {
                                            d_soTien += (d_soTien * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                        }
                                        d_soTien = d_soTien + (d_soTien * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                }
                            } else if (htTraLai.equals("chuyenkhoan")) {
                                if (ngayThucTe < 0) {
                                    d_LaiSuat = ir2Dao.getLaiSuatKyHanThuong();
                                    ngayThucTe = d_kyhantinhlais;
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else if (ngayThucTe == 0) {
                                    ngayThucTe = d_kyhangui;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);
                                    tienLai = (double) (d_soTien * ngayThucTe * 30 * d_LaiSuat) / 36000;
                                    d_soTien += tienLai;
                                } else {
                                    ngayThucTe = d_kyhantinhlais - d_kyhangui + 1;
                                    d_LaiSuat = ir2Dao.getLaiSuatAnLoc(d_kyhangui, soTienGui);

                                    tienLai = (double) (d_soTienBD * d_kyhangui * 30 * d_LaiSuat) / 36000;
                                    int thuong = (int) d_kyhantinhlais / d_kyhangui;

                                    int tt = d_kyhantinhlais - thuong * d_kyhangui;

                                    if (tt == 0) {
                                        tienLai = tienLai * thuong;
                                    } else {
                                        tienLai = tienLai * thuong;
                                        tienLai += (d_soTienBD * ir2Dao.getLaiSuatKyHanThuong() * tt * 30) / 36000;
                                    }
                                    d_soTien += tienLai;
                                }
                            }
                        }
                    }
                } else {

                }
                DecimalFormat df = new DecimalFormat("###,###,###,###.####################");
                String tl = df.format(d_soTien - d_soTienBD);
                return tl;
            }

        }
    }
}
