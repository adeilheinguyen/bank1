<%@page import="controller.Servelet"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="controller.InterestRate2Dao"%>
<%@page import="java.util.Date"%>
<%@page import="controller.PassbookDAO"%>
<%@page import="model.Deal"%>
<%@page import="controller.DealDAO"%>
<%@page import="model.InterestRate2"%>
<%@page import="model.InterestPayment"%>
<%@page import="model.BankNumber"%>
<%@page import="model.Passbook"%>
<%@page import="model.TypePassbook2"%>
<%@page import="model.Withdraw"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String stk = request.getParameter("_sotietkiem");
    String tenChuSo = request.getParameter("_tenChuSo");
    String tklk = request.getParameter("_tklk");
    String sdht = request.getParameter("_sdht");
    String stgtk = request.getParameter("_stgtk");
    String loaitk = request.getParameter("_lhtk");
    String kyhan = request.getParameter("_kyhan");
    String httl = request.getParameter("_httl");
    String ngayms = request.getParameter("_ngaymoso");
    String laisuat = request.getParameter("_laisuat");
    String ngaytt = request.getParameter("_ngayTTgui");
    if (Servelet.getInstance().withDraw(stk, tenChuSo, tklk, sdht, stgtk, loaitk, kyhan, httl, ngayms, laisuat, ngaytt).equals("TC")) {
        response.sendRedirect("detailSB.jsp?wd=true");
    } else {
        response.sendRedirect("detailSB.jsp?wd=false");
    }


%>