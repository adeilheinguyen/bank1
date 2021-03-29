
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
                    <span class="acc-detail-title">Open Savingbook</span>
                </div>
                <form action="processRSB.jsp" method="post" name="processRSB">
                    <div class="form-group">
                        <label class="acc-title">Source account</label>
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
                                <label>Available balance: </label>
                                <div class="control">
                                    <label class="money" autofocus="true" style="margin-left: 71px;"> <span name ="money" id ="money"><%=sdkd%></span></label>
                                </div>
                            </div>
                        </div>

                        <label class="acc-title">CURRENT ACCOUNT</label>
                        <div class="acc-info">
                            <div class="control-group">
                                <div class="control-group">
                                    <label>Select type: </label>
                                    <div class="control">
                                        <select name="loaiTK" id="loaiTK" class="form-control" onchange="giveSelection(this.value)" >
                                            <option value="1">Recurrent savings</option>
                                            <option value="2">Non-term savings</option>
                                            <option value="3">SuperKid</option>
                                            <option value="4">Phat Loc Online</option>
                                            <option value="5">Tai tam - Tai hien có with insurrance</option>
                                            <option value="6">Tai tam - Tai hien có without insurrance</option>
                                            <option value="7">An loc savings</option>
                                        </select>
                                        <br >
                                    </div>

                                    <div class="control-group">
                                        <label>Kỳ hạn gửi: </label>
                                        <div class="control">
                                            <select name="kyhangui" id="kyhangui" class="form-control" onchange="kyhangui()" >
                                                <!-- Có kì hạn -->
                                                <option selected="selected" value="1" data-option="1">1 month</option>
                                                <option value="3" data-option="1">3 months</option>
                                                <option value="6" data-option="1">6 months</option>
                                                <option value="9" data-option="1">9 months</option>
                                                <option value="12" data-option="1">12 months</option>
                                                <option value="24" data-option="1">24 months</option>
                                                <option value="36" data-option="1">36 months</option>

                                                <!--Không kỳ hạn-->
                                                <option value="0" data-option="2">Choose the time</option>
                                                <!-- Superkid -->
                                                <option value="12" data-option="3">12 months</option>

                                                <!-- Phát lộc -->
                                                <option selected="selected" value="1" data-option="4">1 months</option>
                                                <option value="3" data-option="4">3 months</option>
                                                <option value="6" data-option="4">6 months</option>
                                                <option value="9" data-option="4">9 months</option>
                                                <option value="12" data-option="4">12 months</option>
                                                <option value="24" data-option="4">24 months</option>
                                                <option value="36" data-option="4">36 months</option>

                                                <!-- Tài tâm KBH -->
                                                <option selected="selected" value="1" data-option="5">1 months</option>
                                                <option value="3" data-option="5">3 months</option>
                                                <option value="6" data-option="5">6 months</option>
                                                <option value="9" data-option="5">9 months</option>
                                                <option value="12" data-option="5">12 months</option>
                                                <option value="24" data-option="5">24 months</option>
                                                <option value="36" data-option="5">36 months</option>

                                                <option selected="selected" value="1" data-option="6">1 months</option>
                                                <option value="3" data-option="6">3 months</option>
                                                <option value="6" data-option="6">6 months</option>
                                                <option value="9" data-option="6">9 months</option>
                                                <option value="12" data-option="6">12 months</option>
                                                <option value="24" data-option="6">24 months</option>
                                                <option value="36" data-option="6">36 months</option>


                                                <option selected="selected" value="3" data-option="7">3 months</option>
                                                <option value="6" data-option="7">6 months</option>
                                                <option value="9" data-option="7">9 months</option>
                                                <option value="12" data-option="7">12 months</option>
                                            </select>
                                        </div>
                                        <!--<p>Lãi suất: 0 VNĐ</p>--> 
                                    </div>

                                    <div class="control-group">
                                        <label>Number to send: </label>
                                        <div class="control">
                                            <input name="soTienGui" id="soTienGui" type="number" placeholder="Insert number to send" class="money-input form-control">
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
                                                    <option value="chuyenkhoan">Pay interest on account transfer</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label>Note: </label>
                                        <div class="control">
                                            <span class="note">
                                                <p>- Minimum deposit amount: VND 3,000,000. In case the source account is in foreign currency, the minimum deposit amount is converted into VND according to the transfer transfer rate of the branch where the client opens the source account.</p>
                                                <p>- In case the due date and interest payment date coincide with the holidays / holidays: The due date and interest payment date shall be transferred to the nearest working day of Techcombank. The maturity date of the next period is still determined on the basis of the online savings account opening date.</p>
                                                <p>- Validity period of interest rate: applied according to the interest rate regulation at Techcombank Transaction Counter.</p>
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
