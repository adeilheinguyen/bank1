<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="controller.BankNumberDAO"%>
<%@page import="model.BankNumber"%>
<%@page import="model.AccountIB"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="err.jsp"%>
<%
    String successMessage = (String) session.getAttribute("sucMessage");
%>
<%  
    
    String idCus = (String) session.getAttribute("idCus");
    BankNumberDAO bnd = new BankNumberDAO();
    List<BankNumber> listBN = bnd.getAll(Long.parseLong(idCus));
    BankNumber bn = new BankNumber();
    String tentk = "";
    String sotk = "";
    double soduht = 0;
    double sdkd = 0;
    String soduht1 = "";
    String sdkd1 = "";
    String ngaymotk = "";

    DecimalFormat df = new DecimalFormat("#");
    df.setMaximumFractionDigits(12);
    sotk = request.getParameter("nameaccount");

%>

<%@ include file="header.jsp" %>
<div class="content-wrapper">
    <p><span id="successMessage" style="color:red"><%= successMessage%></span></p>
    <div class="content">
        <div class="acc-content">
            <div class="acc-detail">
                <span class="acc-detail-title">Account detail</span>
            </div>
            <form>
                <div class="form-group">
                    <label class="acc-title">Source account</label>
                    <div class="acc-info">
                        <label>Account: </label>
                        <select class=" acc-select form-control" id="tkk" name ="sel1" onchange="tietkiem()">
                            <% for (int i = 0; i < listBN.size(); i++) {%>
                            <option 
                                <% if (sotk == null) { %> 
                                <%} else if (sotk.equals(String.valueOf(listBN.get(i).getNameBN()))) {%> selected ="true"  <% }%>
                                name = "option<%=i%>" value ="<%= listBN.get(i).getNameBN()%>"> <%= listBN.get(i).getNameBN()%></option>
                            <%}%>
                        </select>
                    </div>

                    <!--Sự kiện thay đổi option -->
                    <script>
                        function tietkiem() {
                            var selObj = document.getElementById("tkk");
                            var stk = selObj.options[selObj.selectedIndex].text;
                            window.location.replace("detailaccount.jsp?nameaccount=" + stk);
                        }
                    </script>

                    <%
                        if (sotk == null) {
                            bn = listBN.get(0);
                        } else {
                            bn = bnd.getName(sotk);
                        }
                        sotk = bn.getNameBN();
                        tentk = bn.getCustomer().getName();
                        soduht = bn.getCurrentBalance();
                        soduht1 = df.format(soduht);
                        sdkd = bn.getAvailableBalance();
                        sdkd1 = df.format(sdkd);
                        ngaymotk = bn.getOpenDateAccount();
                    %>

                    <label class="acc-title">CURRENT ACCOUNT:</label>
                    <div class="acc-info">
                        <table class="tbllisting fix_table">
                            <tbody id="loaiTKD">
                                <tr>
                                    <td class="tdlabel">Account holder</td>
                                    <td><span id="lb_Tentaikhoan"><%= tentk%></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Account number</td>
                                    <td><span id="lb_sotaikhoan"><%= sotk%></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Current balance</td>
                                    <td><span id="lb_soduhientai"><%= soduht1%></span></td>
                                </tr>
                                <tr>
                                    <td class="tdlabel">Available balance</td>
                                    <td><span id="lb_sodukhadung"><%= sdkd1%></span></td>
                                </tr>
                                <tr>
                                    <td id="dayTKD" class="tdlabel">Opening date</td>
                                    <td id="dayTKS" style="display:none" class="tdlabel">Ngày mở</td>
                                    <td><span id="lb_ngaymotaikhoan"><%= ngaymotk%></span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>


                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
