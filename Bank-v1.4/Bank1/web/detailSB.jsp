<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.SaveMoney"%>
<%@page import="controller.BankNumberDAO"%>
<%@page import="model.BankNumber"%>
<%@page import="model.AccountIB"%>
<%@page import="model.Passbook"%>
<%@page import="controller.PassbookDAO"%>
<%@page import="controller.DealDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String idCus = (String) session.getAttribute("idCus");
    String id2 = request.getParameter("idCus");
    DealDAO dd = new DealDAO();
    BankNumberDAO bnd = new BankNumberDAO();
    List<SaveMoney> listsm = dd.getPassbook(Long.parseLong(idCus));
    SaveMoney sm = new SaveMoney();
    DecimalFormat df = new DecimalFormat("#");
    df.setMaximumFractionDigits(0);
    String tenChuSo = "", tklk = "", sdht = "", lhtk = "", httl = "", ngayms = "", stgtk = "";
    double ls = 0d;
    int kyhan = 0;
    String index = request.getParameter("index");
%>

<%@ include file="header.jsp" %>
<% if (listsm == null || listsm.size() == 0) { %>
<p align="center"><b>Hãy đăng kí sổ tiết kiệm nếu bạn chưa có sổ!</b></p>
<%} else {%>
<div class="content-wrapper">
    <div class="content">
        <div class="acc-content">
            <div class="acc-detail">
                <span class="acc-detail-title">Chi tiết sổ tiết kiệm</span>
            </div>
            <form  method="POST" action="processDetailSB.jsp">
                <div class="form-group">
                    <label class="acc-title">Tài nguyên tài khoản</label>
                    <div class="acc-info">
                        <label>Chọn sổ tiết kiệm </label>
                        <select class=" acc-select form-control" name="stk" id="stk" onchange="opt()">
                            <% for (int i = 0; i < listsm.size(); i++) {%>
                            <option 
                                <% if (index == null) { %> 
                                <%} else if (index.equals(String.valueOf(i))) {%> selected ="true"  <% }%>
                                name = "a<%=i%>" id = "a<%=i%>" value ="<%= i%>"><%= listsm.get(i).getDeal().getPassbook().getName()%></option>
                            <%}%>
                        </select>
                    </div>
                    <%
                        if (index == null) {
                            sm = listsm.get(0);
                        } else {
                            sm = listsm.get(Integer.parseInt(index));
                        }
                        tenChuSo = sm.getDeal().getBankNumber().getCustomer().getName();
                        tklk = sm.getDeal().getBankNumber().getNameBN();
                        sdht = df.format(sm.getDeal().getBankNumber().getCurrentBalance());
                        lhtk = sm.getDeal().getTp2().getTentk();
                        kyhan = sm.getDeal().getIr().getKyHanGui();
                        httl = sm.getDeal().getIp().getTypeIP();
                        ngayms = sm.getDeal().getPassbook().getOpenDateSB();
                        ls = sm.getDeal().getIr().getLaiSuat();
                        stgtk = df.format(sm.getDeposits());
                    %>
                    <label class="acc-title">Tiền thanh toán</label>
                    <div class="acc-info">
                        <table class="tbllisting fix_table">
                            <tbody id="loaiTKD">
                                <tr>
                                    <td class="tdlabel">Chủ sổ tiết kiệmr</td>
                                    <td><label id="lb_tenchuso" name="lb_tenchuso"><%= tenChuSo%></label></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Tài khoản đã liên kết</td>
                                    <td><span id="lb_taikhoanlienket" name="lb_taikhoanlienket"><%= tklk%></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Số dư hiện tại</td>
                                    <td><span id="lb_soduhientai" name="lb_soduhientai"><%= sdht%></span> VND</td>

                                </tr>
                                <tr>
                                    <td class="tdlabel">Số dư tài khoản</td>
                                    <td><span id="lb_sotgtk" name="lb_sotgtk"><%= stgtk%></span> VND</td>

                                </tr>
                                <tr>
                                    <td class="tdlabel">Loại hình tiết kiệm</td>
                                    <td><dispanv id="lb_loaihinhtietkiem" name="lb_loaihinhtietkiem"><%= lhtk%></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Kỳ hạn</td>
                                    <td><label name="lb_kyhan" id="lb_kyhan" value="<%= kyhan%>"><%= kyhan%></label> month</td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Hình thức trả lãi</td>
                                    <td><span name="lb_hinhthuctralai" id="lb_hinhthuctralai"><%= httl%></span></td>
                                </tr>
                                <tr>
                                    <td id="dayTKD" class="tdlabel" >Ngày mở sổ</td>
                                    <td><span name="lb_ngaymoso" id="lb_ngaymoso"><%= ngayms%></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel" >Thời gian</td>
                                    <td><span name="time_span" id="time_span"></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Ngày gửi</td>
                                    <td><span name="chechlechtime" id="chechlechtime"></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Lãi suất</td>
                                    <td><span name="lb_laisuat" id="lb_laisuat"><%= ls%>%</span></td>
                                </tr>

                                </tbody>
                                
                        </table>
                    </div>
                    <div class="btn-confirm">
                        <button id="rutTien" type="submit" class="btn btn-default">Withdrawal</button>
                    </div>
                </div>
                <div style="display: none">
                    <input type="text" name="_sotietkiem" id="_sotietkiem" value="" />
                    <input type="text" name="_tenChuSo" id="_tenChuSo" value="<%= tenChuSo%>" />
                    <input type="text" name="_tklk" id="_tklk" value="<%= tklk%>" />
                    <input type="text" name="_sdht" id="_sdht"  value="<%= sdht%>"/>
                    <input type="text" name="_stgtk" id="_stgtk"  value="<%= stgtk%>"/>
                    <input type="text" name="_lhtk" id="_lhtk" value="<%= lhtk%>"  />
                    <input type="text" name="_kyhan" id="_kyhan" value="<%= kyhan%>"  />
                    <input type="text" name="_httl" id="_httl"  value="<%= httl%>" />
                    <input type="text" name="_ngaymoso" id="_ngaymoso" value="<%= ngayms%>" />
                    <input type="text" name="_laisuat" id="_laisuat" value="<%= ls%>"  />
                    <input type="text" name="_ngayTTgui" id="_ngayTTgui" value="" />

                </div>
            </form>
        </div>
    </div>
</div>
<%}%>
<script>
    function opt() {
        var stk = document.getElementById("stk");
        var i = stk.options[stk.selectedIndex].value;
        window.location.replace("detailSB.jsp?index=" + i);
    }
</script> 
<!---->
<%
    String isSuccess = (String) request.getParameter("wd");
    if (isSuccess != null) {
%>
<script>
    function rutSoThanhCong() {
        alert("Success");
    }
    rutSoThanhCong();
</script>
<%}%>
<%@ include file="footer.jsp" %>

<script>
    $(document).ready(function () {
        var b = $("#stk>option:selected").text();
        $('#_sotietkiem').val(b);
        $('#stk').change(function () {
            var a = $("#stk>option:selected").text();
            $('#_sotietkiem').val(a);
        });

        var a = $('#lb_ngaymoso').html();
        function timer() {
            var currentTime = new Date();
            var year = currentTime.getFullYear();
            var month = currentTime.getMonth() + 1;
            var day = currentTime.getDate();
            var hours = currentTime.getHours();
            var minutes = currentTime.getMinutes();
            var sec = currentTime.getSeconds();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            if (hours < 10) {
                hours = "0" + hours;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (sec < 10) {
                sec = "0" + sec;
            }
            var t_str = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + sec + "";
            document.getElementById('time_span').innerHTML = t_str;
            setTimeout(timer, 1000);
        }
        timer();
        function daysBetween() {
            var date1 = new Date(a);
            var date2 = new Date();
            var date1_ms = date1.getTime();
            var date2_ms = date2.getTime();
            var difference_ms = date2_ms - date1_ms;
            var diffSeconds = Math.floor(difference_ms / 1000 % 60);
            var diffMinutes = Math.floor(difference_ms / (60 * 1000) % 60);
            var diffHours = Math.floor(difference_ms / (60 * 60 * 1000) % 24);
            var diffDays = Math.floor(difference_ms / (24 * 60 * 60 * 1000));
            var str = diffDays + ' days, ' + diffHours + ' hours, ' + diffMinutes + ' minutes, and ' + diffSeconds + ' seconds';
            document.getElementById('chechlechtime').innerHTML = str;
            document.getElementById('_ngayTTgui').value = diffDays;
            setTimeout(daysBetween, 1000);
        }
        daysBetween();
    });

</script>


