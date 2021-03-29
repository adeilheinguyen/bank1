<%@page import="controller.Servelet"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String taiKhoan = request.getParameter("taikhoan");
    String loaiHTGTK = request.getParameter("loaiTK");
    String khg = request.getParameter("kyhangui");
    String soTienGui = request.getParameter("soTienGui");
    String sdht = request.getParameter("money");
    String hinhThuTraLai = request.getParameter("httl");
    if (soTienGui.equals("")) {
        response.sendRedirect("registerSB.jsp?errSTG=" + "Please enter number of money");
    } else {
        String infor = Servelet.getInstance().openSB(taiKhoan, loaiHTGTK, khg, soTienGui, sdht, hinhThuTraLai);
        if (infor.equals("Open Successful")) {
            session.setAttribute("successMessage","Open SB Successful!");
            response.sendRedirect("registerSB.jsp?mtc=1");
        } else {
            response.sendRedirect("registerSB.jsp?errSTG=" + infor);
        }
    }

%>