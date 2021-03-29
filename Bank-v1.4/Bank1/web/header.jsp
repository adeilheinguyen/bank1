<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String username1 = (String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>TechcomBank</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
        <link rel="stylesheet" href="css/jquery-ui.min.css" 
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/owl.carousel.min.css" />
        <link rel="stylesheet" href="css/animate.css"/>
        <link rel="stylesheet" href="css/main.css" />

        <style type="text/css">
            .html-marquee {
                background-color:#FFF;
                width:100%;
                font-family:Times;
                font-size:14px;
                font-weight:bold;
                font-weight:bold;
                color:#555555;
                text-transform:capitalize;
            }
        </style>
    </head>
    <body>

        <header>
            <div class="banner">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 banner-logo">
                            <img src="img/logo.png">
                        </div>
                    </div>
                </div>
            </div>
            <div style="background-color: #FFF;margin-bottom: -8px">
                <% if(username1 != null){%>
                <marquee class="html-marquee" direction="left" sbehavior="scroll" scrollamount="8">
                   Hello: ${username}</marquee>
                <%}%>
            </div>
            <div class="menu">
                <ul>
                    <li class="home">
                        <a href="detailaccount.jsp?idCus=${idCus}">
                            <i class="fa fa-home" aria-hidden="true"></i>
                        </a>
                    </li>
                    <li>
                        <a href="detailaccount.jsp?idCus=${idCus}">
                            Chi tiết tài khoản
                        </a>
                    </li>
                    <li>
                        <a id="moTaiKhoan" href="registerSB.jsp?idCus=${idCus}">
                            Mở sổ tiết kiệm
                        </a>
                    </li>
                    <li>
                        <a id="thongTinTk" href="detailSB.jsp?idCus=${idCus}">
                            Chi tiết sổ tiết kiệm
                        </a>
                    </li>
                    <li>
                        <a href="interestrate.jsp">
                            Tính lãi suất
                        </a>
                    </li>
                    <li>
                        <a href="index.jsp">
                            <img src="img/icon-home.png" alt="">
                        </a>
                    </li>

                </ul>
            </div>
        </header>
