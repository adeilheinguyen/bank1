<%@page import="controller.CustomerDAO"%>
<%@page import="model.Customer"%>
<%@page import="controller.AccountDAO"%>
<%@page import="model.AccountIB"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String nameaccount = request.getParameter("nameaccount");
    String password = request.getParameter("password");
    String fs = "Login fail!";
    String suc = "Login success!";
    if (nameaccount.equals("") || password.equals("")) {
        session.setAttribute("errMessage", fs);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } else {
        session.setAttribute("sucMessage", suc);
        AccountIB aib = new AccountIB(nameaccount, password);
        AccountDAO ad = new AccountDAO();

        CustomerDAO cd = new CustomerDAO();

        boolean check = ad.checkLogin(aib);
        if (check == true) {
            String ct = cd.getByUsername(nameaccount);
            long idCus = cd.getIDCustormerByName(nameaccount);
//            System.out.println(ct +";;"+idCus);
            if (ct == null || idCus == 0) {
                session.setAttribute("errMessage", fs);
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("idCus", idCus+"");
                session.setAttribute("username", ct);
                response.sendRedirect("detailaccount.jsp");
            }
        } else {
            session.setAttribute("errMessage", fs);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

%>