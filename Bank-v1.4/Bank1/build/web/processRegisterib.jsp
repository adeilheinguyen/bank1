<%@page import="controller.Servelet"%>
<%@page import="controller.SendDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%@page import="controller.AccountDAO"%>
<%@page import="model.AccountIB"%>
<%
    String nameAcc = request.getParameter("nameaccount");
    String pass = request.getParameter("password");
    String idxn = request.getParameter("idxn");
//    int i_idxn = Integer.parseInt(idxn);
    String email = request.getParameter("email");
    String idxn1 = request.getParameter("idxn1");
//    int i_idxn1 = Integer.parseInt(idxn1);
    String isTC = Servelet.getInstance().registerIB(nameAcc, pass,idxn,email,idxn1);
    System.out.println(isTC);
    if (isTC.equals("Success")) {
       response.sendRedirect("index.jsp");
    } else {
       response.sendRedirect("registerib.jsp?false="+isTC);
    }

%>