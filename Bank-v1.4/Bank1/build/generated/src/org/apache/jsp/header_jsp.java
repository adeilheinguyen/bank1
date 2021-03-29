package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"err.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');

    String username1 = (String) session.getAttribute("username");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>TechcomBank</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("       \n");
      out.write("        <link rel=\"stylesheet\" href=\"css/jquery-ui.min.css\" \n");
      out.write("        <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/font-awesome.min.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/owl.carousel.min.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/animate.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/main.css\" />\n");
      out.write("\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("            .html-marquee {\n");
      out.write("                background-color:#FFF;\n");
      out.write("                width:100%;\n");
      out.write("                font-family:Times;\n");
      out.write("                font-size:14px;\n");
      out.write("                font-weight:bold;\n");
      out.write("                font-weight:bold;\n");
      out.write("                color:#555555;\n");
      out.write("                text-transform:capitalize;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <header>\n");
      out.write("            <div class=\"banner\">\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-lg-6 col-md-6 banner-logo\">\n");
      out.write("                            <img src=\"img/logo.png\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div style=\"background-color: #FFF;margin-bottom: -8px\">\n");
      out.write("                ");
 if(username1 != null){
      out.write("\n");
      out.write("                <marquee class=\"html-marquee\" direction=\"left\" sbehavior=\"scroll\" scrollamount=\"8\">\n");
      out.write("                   Hello: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${username}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</marquee>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"menu\">\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"home\">\n");
      out.write("                        <a href=\"detailaccount.jsp?idCus=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${idCus}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            <i class=\"fa fa-home\" aria-hidden=\"true\"></i>\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"detailaccount.jsp?idCus=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${idCus}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            Chi tiết tài khoản\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a id=\"moTaiKhoan\" href=\"registerSB.jsp?idCus=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${idCus}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            Mở sổ tiết kiệm\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a id=\"thongTinTk\" href=\"detailSB.jsp?idCus=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${idCus}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            Chi tiết sổ tiết kiệm\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"interestrate.jsp\">\n");
      out.write("                            Tính lãi suất\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"index.jsp\">\n");
      out.write("                            <img src=\"img/icon-home.png\" alt=\"\">\n");
      out.write("                        </a>\n");
      out.write("                    </li>\n");
      out.write("\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
