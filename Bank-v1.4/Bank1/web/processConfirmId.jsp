<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="controller.Servelet"%>
<%@page import="controller.BankNumberDAO"%>
<%@page import="controller.CustomerDAO"%>
<%@page import="controller.SendDAO"%>

<%
    String nameAcc = request.getParameter("nameaccount1");
    String emailfrom = request.getParameter("email1");
    SendDAO sd = new SendDAO();
    int rd = sd.rand();
    int fixedNumberForTest = 12345;
    String numbers = Servelet.getInstance().checkConfirmIR(nameAcc, emailfrom, rd);
    session.setAttribute("nameaccount",nameAcc);
    session.setAttribute("email", emailfrom);
    if (!numbers.equals("Get code successful") || nameAcc.isEmpty() || emailfrom.isEmpty()) {
        response.sendRedirect("registerib.jsp?false="+numbers);
    }else{
        session.setAttribute("numbers", String.valueOf(rd));
        session.setAttribute("numbers1", String.valueOf(fixedNumberForTest));
        response.sendRedirect("registerib.jsp?ok=1");
    }

%>
