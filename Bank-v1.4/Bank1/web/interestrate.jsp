
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String tienLai = (String) request.getParameter("tienLai");
%>
<%@ include file="header.jsp" %>
<div class="content-wrapper">
    <div class="content">
        <div class="acc-content">
            <div class="acc-detail">
                <span class="acc-detail-title">Tỷ suất lợi nhuận trực tuyến</span>
            </div>
            <div class="rate-count-wrapper">
                <form id="rate-count" name="rate-account" action="processIR.jsp" method="POST">
                    <table>
                        <tbody>
                            <tr>
                                <td><span class="heading">Số tiền</span></td>
                                <td><input type="number" name="sotien" autofocus="true" id="sotien" class="form-control"  title=""></td>
                                <td>VND</td>
                            </tr>
                            <tr>
                                <td><span class="heading">Lựa chọn loại</span></td>
                                <td>
                                    <select name="loaiTK" id="loaiTK" class="form-control" onchange="giveSelection(this.value)" >
                                        <option value="1">Lãi suất định kỳ</option>
                                        <option value="2">Lãi suất không kỳ hạn</option>
                                        <option value="3">SuperKid</option>
                                        <option value="4">Phát Lộc Online</option>
                                        <option value="5">Tài tâm - Tài hiền với bảo hiểm</option>
                                        <option value="6">Tài tâm - Tài hiền không kèm bảo hiểm</option>
                                        <option value="7">Lãi suất An Lộc</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="heading">Chọn một kỳ hạn hợp đồng</span></td>
                                <td>
                                    <select name="kyhangui" id="kyhangui" class="form-control" onchange="kyhangui()" >
                                        <!-- Có kì hạn -->
                                        <option selected="selected" value="1" data-option="1">1 tháng</option>
                                        <option value="3" data-option="1">3 tháng</option>
                                        <option value="6" data-option="1">6 tháng</option>
                                        <option value="9" data-option="1">9 tháng</option>
                                        <option value="12" data-option="1">12 tháng</option>
                                        <option value="24" data-option="1">24 tháng</option>
                                        <option value="36" data-option="1">36 tháng</option>

                                        <!--Không kỳ hạn-->
                                        <option value="0" data-option="2">Chọn thời gian</option>
                                        <!-- Superkid -->
                                        <option value="12" data-option="3">12 tháng</option>

                                        <!-- Phát lộc -->
                                        <option selected="selected" value="1" data-option="4">1 tháng</option>
                                        <option value="3" data-option="4">3 tháng</option>
                                        <option value="6" data-option="4">6 tháng</option>
                                        <option value="9" data-option="4">9 tháng</option>
                                        <option value="12" data-option="4">12 tháng</option>
                                        <option value="24" data-option="4">24 tháng</option>
                                        <option value="36" data-option="4">36 tháng</option>

                                        <!-- Tài tâm KBH -->
                                        <option selected="selected" value="1" data-option="5">1 tháng</option>
                                        <option value="3" data-option="5">3 tháng</option>
                                        <option value="6" data-option="5">6 tháng</option>
                                        <option value="9" data-option="5">9 tháng</option>
                                        <option value="12" data-option="5">12 tháng</option>
                                        <option value="24" data-option="5">24 tháng</option>
                                        <option value="36" data-option="5">36 tháng</option>

                                        <option selected="selected" value="1" data-option="6">1 tháng</option>
                                        <option value="3" data-option="6">3 tháng</option>
                                        <option value="6" data-option="6">6 tháng</option>
                                        <option value="9" data-option="6">9 tháng</option>
                                        <option value="12" data-option="6">12 tháng</option>
                                        <option value="24" data-option="6">24 tháng</option>
                                        <option value="36" data-option="6">36 tháng</option>


                                        <option selected="selected" value="3" data-option="7">3 tháng</option>
                                        <option value="6" data-option="7">6 tháng</option>
                                        <option value="9" data-option="7">9 tháng</option>
                                        <option value="12" data-option="7">12 tháng</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="heading">Nhập vào kỳ hạn tính lãi</span></td>
                                <td><input type="number" name="kyhantinhlai"  id="kyhantinhlai" class="form-control"></td>
                                <td>
                                    <select name="chooseTinhLai" id="chooseTinhLai" onchange="chooseTinhLai()">
                                        <option value="thang">Tháng</option>
                                        <option value="ngay">Ngày</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td><span class="heading">Chọn phương thức thanh toán</span></td>
                                <td>
                                    <select name="htTraLai" id="htTraLai" class="form-control" onchange="htTraLai()" >
                                        <option value="nhapgoc">Thanh toán lãi gốc</option>
                                        <option value="chuyenkhoan">Thanh toán lãi dựa trên chuyển khoản </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
<<<<<<< HEAD
                                <td><span name="kq"class="heading">Tiền lãir</span></td>
=======
                                <td><span name="kq"class="heading">Tiền lãi</span></td>
>>>>>>> 2e756e8effd613f537a96a551b766166e69b6cda

                                <td><span id="tienlai" contenteditable="false">
                                        <% if (tienLai != null) {%>
                                        <%= tienLai %> <%}%> 
                                    </span></td>
                                <td>VND</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="count-btn">
                        <input id ="calculate" type="submit" value="Calculate" class="btn btn-default" />
                    </div>
                </form>
            </div>
            <div class="rate-count-result">
                <h1>Chi tiết lãi suất cho từng loại tiết kiệm</h1>
                <ul>
                    <li>
                        <h2>I. Lãi suất tiết kiệm định kỳ</h2>
                        <img src="img/phatloc.jpg" class="cus-img-1" alt="">
                    </li>
                    <li>
                        <h2>III. Lãi suất tiết kiệm không kỳ hạn</h2>
                        <img src="img/khongkihan_1.png" class="cus-img-2" alt="">
                    </li>
                    <li>
                        <h2>III. Lãi suất tiết kiệm Superkid</h2>
                        <img src="img/superkid.png" class="cus-img-2" alt="">
                    </li>
                    <li>
                        <h2>IV. Lãi suất tiết kiệm Phát Lộc Online</h2>
                        <img src="img/phatloconline.jpg" alt="">
                        <h2>V. Lãi suất Tài tâm - Tài hiền có bảo hiểm</h2>
                        <img src="img/taitamtaihiencbh.png" class="cus-img-3" alt="">
                    </li>
                    <li>
                        <h2>VI. Lãi suất Tài tâm - Tài hiền không có bảo hiểm</h2>
                        <img src="img/taitamtaihienkbh.png" class="cus-img-3" alt="">
                    </li>
                    <li>
                        <h2>VII. Lãi suất An Lộc</h2>
                        <img src="img/anloc.png" alt="">
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var sel1 = document.querySelector('#loaiTK');
    var sel2 = document.querySelector('#kyhangui');
    var options2 = sel2.querySelectorAll('option');

    function giveSelection(selValue) {
        sel2.innerHTML = '';
        for (var i = 0; i < options2.length; i++) {
            if (options2[i].dataset.option === selValue) {
                sel2.appendChild(options2[i]);
            }
        }
    }

    giveSelection(sel1.value);
</script>
<%@ include file="footer.jsp" %>
