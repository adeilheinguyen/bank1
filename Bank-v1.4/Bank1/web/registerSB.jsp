
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="controller.BankNumberDAO"%>
<%@page import="model.BankNumber"%>
<%@page import="model.AccountIB"%>
<%@page import="model.AccountIB"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String idCus = (String) session.getAttribute("idCus");
    BankNumberDAO bnd = new BankNumberDAO();
    List<BankNumber> listBN = bnd.getAll(Long.parseLong(idCus));
    if (listBN == null) {
%>
<jsp:forward page="detailaccount.jsp"></jsp:forward>
<%} else {

    }
    BankNumber bn = new BankNumber();
    String tentk = "";
    String sotk = "", sdkd = "";
    double d_sdkd = 0;
    DecimalFormat df = new DecimalFormat("#");
    df.setMaximumFractionDigits(12);
    String errSTG = (String) request.getParameter("errSTG");
    String successMessage = (String) request.getParameter("successMessage");
    sotk = request.getParameter("nameaccount");
%>
<%@ include file="header.jsp" %>
    <div class="content-wrapper" >

        <div class="content">
            <div class="acc-content">
                <div class="acc-detail">
                    <span class="acc-detail-title">Mở sổ tiết kiệm</span>
                </div>
                <form action="processRSB.jsp" method="post" name="processRSB">
                    <div class="form-group">
                        <label class="acc-title">Tài khoản nguồn</label>
                        <div class="acc-info">
                            <div class="control-group">
                                <label>Account: </label>
                                <select class=" acc-select form-control" id="taikhoan" name ="taikhoan" onchange="tietkiem()">
                                    <% for (int i = 0; i < listBN.size(); i++) {%>
                                    <option 
                                        <% if (sotk == null) { %> 
                                        <%} else if (sotk.equals(String.valueOf(listBN.get(i).getNameBN()))) {%> selected ="true"  <% }%>
                                        name = "option<%=i%>" value ="<%= listBN.get(i).getNameBN()%>"> <%= listBN.get(i).getNameBN()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%
                                if (sotk == null) {
                                    bn = listBN.get(0);
                                } else {
                                    bn = bnd.getName(sotk);
                                }
                                d_sdkd = bn.getAvailableBalance();
                                sdkd = df.format(d_sdkd);
                            %>
                            <div class="control-group">
                                <label>Số dư khả dụng: </label>
                                <div class="control">
                                    <label class="money" autofocus="true" style="margin-left: 71px;"> <span name ="money" id ="money"><%=sdkd%></span></label>
                                </div>
                            </div>
                        </div>

                        <label class="acc-title">Tài khoản hiện thời</label>
                        <div class="acc-info">
                            <div class="control-group">
                                <div class="control-group">
                                    <label>Chọn loại: </label>
                                    <div class="control">
                                        <select name="loaiTK" id="loaiTK" class="form-control" onchange="giveSelection(this.value)" >
                                            <option value="1">Tiết kiệm định kỳ</option>
                                            <option value="2">Tiết kiệm không kỳ hạn</option>
                                            <option value="3">SuperKid</option>
                                            <option value="4">Phát Lộc Online</option>
                                            <option value="5">Tài tâm - Tài hiền có bảo hiểm</option>
                                            <option value="6">Tài tâm - Tài hiền không có bảo hiểm</option>
                                            <option value="7">Tiết kiệm An Lộc</option>
                                        </select>
                                        <br >
                                    </div>

                                    <div class="control-group">
                                        <label>Kỳ hạn gửi: </label>
                                        <div class="control">
                                            <select name="kyhangui" id="kyhangui" class="form-control" onchange="kyhangui()" >
                                                <!-- Có kì hạn -->
                                                <option selected="selected" value="1" data-option="1">1 month</option>
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
                                        </div>
                                        <!--<p>Lãi suất: 0 VNĐ</p>--> 
                                    </div>

                                    <div class="control-group">
                                        <label>Number to send: </label>
                                        <div class="control">
                                            <input name="soTienGui" id="soTienGui" type="number" placeholder="Nhập số tiền gửi" class="money-input form-control">
                                        </div>
                                        <span>VND</span>
                                    </div>
                                    <div class="control-group">
                                        <label></label>
                                        <div class="control">
                                            <% if (errSTG != null) {%>
                                            <p><span id="failOpenSB" style="color:red"><%= errSTG%></span></p>
                                                <% }%>
                                                <% if (successMessage != null) {%>
                                            <p><span id="successOpenSB" style="color:red"><%= successMessage%></span></p>
                                                <% }%>
                                        </div>

                                    </div>

                                    <div class="control-group">
                                        <label>Hình thức trả lãi: </label>
                                        <div class="control">
                                            <select name="httl" id="httl" onchange="httl()" class="acc-select form-control">
                                                 <option value="nhapgoc">Pay original interest</option>
                                                    <option value="chuyenkhoan">Trả lãi khi chuyển khoản</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label>Note: </label>
                                        <div class="control">
                                            <span class="note">
                                                <p>- Số tiền gửi tối thiểu: 3.000.000 VND. Trường hợp tài khoản nguồn bằng ngoại tệ, số tiền ký quỹ tối thiểu quy đổi ra VND theo tỷ giá chuyển khoản của chi nhánh nơi khách hàng mở tài khoản nguồn.</p>
                                                <p>- Trường hợp ngày đến hạn và ngày trả lãi trùng vào ngày nghỉ, ngày lễ: Ngày đến hạn và ngày trả lãi được chuyển sang ngày làm việc gần nhất của Techcombank. Ngày đáo hạn của kỳ tiếp theo vẫn được xác định trên cơ sở ngày mở tài khoản tiết kiệm trực tuyến.</p>
                                                <p>- Thời hạn hiệu lực của lãi suất: áp dụng theo quy định về lãi suất tại quầy giao dịch Techcombank.</p>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group form-submit" style="margin-top:20px">
                                <label class="control-label"></label>
                                <div class="control">
                                    <button id="btnSubmitStep1" type="submit" class="btn btn-default">Submit</button>
                                </div>
                            </div>
                            </form>
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
            <script>
                function tietkiem() {
                    var selObj = document.getElementById("taikhoan");
                    var stk = selObj.options[selObj.selectedIndex].text;
                    window.location.replace("registerSB.jsp?nameaccount=" + stk);
                }
            </script>
            <%
                String isSuccess = (String) request.getParameter("mtc");
                if (isSuccess != null) {
            %>
            <script>
                function moTKThanhCong() {
                    alert("Mở sổ thành công");
                }
                moTKThanhCong();
            </script>
            <%}%>
        </div>
        </div>
            <%@ include file="footer.jsp" %>
