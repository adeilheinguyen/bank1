<%@page import="controller.Servelet"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="controller.InterestRate2Dao"%>
<%@page import="model.InterestRate2"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
        //L?y s? ti?n c?n tính
        String soTien = request.getParameter("sotien");
        //L?y lo?i hình th?c g?i ti?t ki?m
        String loaiTK = request.getParameter("loaiTK").toString();
        String kyhangui = request.getParameter("kyhangui");
        String kyhantinhlai = request.getParameter("kyhantinhlai");
        String chooseTinhLai = request.getParameter("chooseTinhLai").toString();
        String htTraLai = request.getParameter("htTraLai").toString();
        String tl = Servelet.getInstance().inRate(soTien, loaiTK, kyhangui, kyhantinhlai, chooseTinhLai, htTraLai);
        DecimalFormat df = new DecimalFormat("###,###,###,###.####################");
        response.sendRedirect("interestrate.jsp?tienLai=" + tl);

%>