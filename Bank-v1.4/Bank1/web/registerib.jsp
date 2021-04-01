<%@page import="controller.SendDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    String numbers = (String) session.getAttribute("numbers");
    String numbers1 = (String) session.getAttribute("numbers1");
    System.out.println(numbers);
    String nameAcc = (String) session.getAttribute("nameaccount");
    String email = (String) session.getAttribute("email");
    String errPass = (String) request.getParameter("false");
   String err2 = (String) request.getParameter("ok");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>TechcomBank</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script> 
        <link rel="stylesheet" href="css/jquery-ui.min.css" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/owl.carousel.min.css" />
        <link rel="stylesheet" href="css/animate.css" />
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

            <div class="menu">
                <ul>
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
        <div class="content-wrapper">
            <div class="content">
                <div class="acc-content" >
                    <div class="acc-detail">
                        <span class="acc-detail-title">Đăng ký</span>
                    </div>
                    <form name="myform" action="processRegisterib.jsp" method="post" onsubmit="return validation()">
                        <div class="form-group">
                            <label class="acc-title">Chi tiết tài khoản</label>
                            <div class="sign-up-wrapper">
                                <table class="acc-signup ">
                                    <tbody>
                                        <tr>
                                            <td>Số tài khoản: </td>
                                            <td><input type="number" class="form-control signup-input" autofocus="true" name="nameaccount" id="nameaccount" value ="${nameaccount}"/></td>
                                        </tr>
                                        <tr>
                                            <td>Password</td>
                                            <td><input type="password" class="form-control signup-input" name="password" id="password"/></td>
                                        </tr>
                                        
                                        <tr>
                                            <td>Email:</td>
                                            <td><input type="text" class="form-control signup-input" name="email" id="email" value="${email}"></td>
                                        </tr>
                                        <tr>
                                            <td>Confirm code</td>
                                            <td><input type="number" class="form-control signup-input" name="idxn" id="idxn" ></td>
                                            <td><input type="number" style="display: none" class="form-control signup-input" name="idxn1" id="idxn1" value="${numbers}"></td>
                                            <td><input type="number" style="display: none" class="form-control signup-input" name="idxn1" id="idxn2" value="${numbers1}"></td>
                                            
                                           
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><% if (errPass != null) {%>
                                                <p><span id="failRegister" style="color:red"><%= errPass %></span></p>
                                                <% }%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div align="right">
                            <input class="btn btn-default " id="xacnhan" name="xacnhan" type="submit" value="Submit" />
                        </div>
                    </form>



                    <form  name="myform1" action="processConfirmId.jsp" method="POST" onsubmit="return validation1()">
                        <div class="form-group" style="display: none">
                            <div class="sign-up-wrapper">
                                <table class="acc-signup ">
                                    <tbody>
                                        <tr>
                                            <td>Số tài khoản</td>
                                            <td><input type="number" class="form-control signup-input" name="nameaccount1" id="nameaccount1" value =""/></td>
                                        </tr>
                                        <tr>
                                            <td>Email đăng ký:</td>
                                            <td><input type="text" class="form-control signup-input" name="email1" id="email1" value=""/></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div align="right">
                            <input type="submit" class="btn btn-default" id ="btnlayma1" name="btnlayma" value="Get code" style="position: absolute; bottom: 90px; right: 30px;"/>
                            
                        </div>
                    </form>

                    <script type="text/javascript">
                        function validation1() {
                            var x = document.myform.nameaccount.value;
                            var z = document.myform.email.value;

                            if (x === null || x === "" || x.length <= 0) {
                                alert("Vui lòng nhập số tài khoản!!");
                                return false;
                            } else if (z === null || z === "") {
                                alert("Vui lòng nhập email!!");
                                return false;
                            } else {
                                document.myform1.submit();
                            }
                        }

                        function validation() {
                            var x = document.myform.nameaccount.value;
                            var y = document.myform.password.value;
                            var z = document.myform.email.value;
                            var t = document.myform.idxn.value;
                            var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

                            if (x === null || x === "" || x.length <= 0) {
                                alert("Vui lòng nhập số tài khoản!!");
                                return false;
                            } else if (y === null || y === "") {
                                alert("Vui lòng nhập mật khẩu!!");
                                return false;
                            } else if (z === null || z === "") {
                                alert("Vui lòng nhập email!!");
                                return false;
                            } else if (!filter.test(z)) {
                                alert('Vui lòng nhập đúng email!!');
                                z.focus;
                                return false;
                            } else if (t === null || t === "") {
                                alert("Vui lòng nhập mã xác nhận!!");
                                return false;
                            } else {
                                document.myform.submit();
                            }
                        }
                    </script>

                </div>
            </div>
        </div>
        <footer>
            <div class="footer-text">
               <div class="footer-text">
        <div class="left">
            <p><a onclick="alert('Function is under construction')" href="">Techcombank</a> | Hotline: 1900 54 54 13</p>
        </div>
        <div class="right">
            <p class="lienhe"><a href="" onclick="alert('Function is under construction')">Hướng dẫn sử dụng dịch vụ</a> | <a target="_blank" href="#">Instructions for safe trading</a></p>
        </div>
    </div>
            </div>
        </footer>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/custom.js"></script>
         <%if(err2 != null){ %>
         <script>
             alert("Lấy mã thành công");
         </script>
         <% } %>
    </body>
</html>
