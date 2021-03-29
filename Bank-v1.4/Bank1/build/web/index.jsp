<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ngân hàng Techcombank</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/jquery-ui.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/main.css">

    </head>

    <body class="signin-body">
       

        <div class="owl-carousel owl-theme sign-in-banner">
            <div class="item img-1"></div>
            <div class="item img-2"></div>
            <div class="item img-3"></div>	
        </div>
        <div class="box-login">
            <div class="content-login">
                <div class="logo">
                    <img src="img/logo.png" alt="">
                </div>
            </div>
            <div class="signin-info">
                <form method="post" action="processLogin.jsp" >
                    <input class="form-control" type="text" placeholder="Username" autofocus="true" name="nameaccount"><br>
                    <input class="form-control" type="password" placeholder="Password" name="password"><br>

                    <% 
                        String err = (String) session.getAttribute("errMessage");
                        if (err != null) {%>
                        <p><span id="message" style="color:red"><%= err %></span></p>
                        <% }%>
                    <input class="btn btn-default" id="lg" type="submit" value="Login" /><br>

                </form>
            </div>
            <p id="err-ms"></p>
            <div class="signin-link">
                <p>
                    <a href="" onclick="alert('Function is under construction')">Quên mất khậu à?</a>
                </p>
                <p>
                    <a href="registerib.jsp">Đăng ký</a>
                </p>
                <p>
                    <a href="interestrate.jsp">Tính lãi suất</a>
                </p>
            </div>
        </div>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/moment.js"></script>
        <script src="js/moment.min.js"></script>
        <script src="js/moment-with-locales.js"></script>
        <script src="js/moment-with-locales.min.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>