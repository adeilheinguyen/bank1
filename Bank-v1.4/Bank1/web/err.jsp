<%-- 
    Document   : err
    Created on : Mar 6, 2019, 1:07:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chức năng đang được xây dựng</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="js/bootstrap.min.js"></script>
        <script src="jquery-3.3.1.min.js"></script>
        <style>
            .error-template {padding: 40px 15px;text-align: center;}
            .error-actions {margin-top:15px;margin-bottom:15px;}
            .error-actions .btn { margin-right:10px; }
        </style>
    </head>
    <body>


        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="error-template">
                        <h1>
                            Oops!</h1>
                        <h2>
                            404 Not Found</h2>
                        <div class="error-details">
                            Có lỗi xảy ra, xin vui lòng thử lại!
                        </div>
                        <div class="error-actions">
                            <a href="index.jsp" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                              Về trang chủ </a><a href="index.jsp" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Liên hệ hỗ trợ </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
