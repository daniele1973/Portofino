<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"
%><%@ taglib prefix="mde" uri="/manydesigns-elements"
%><%@ taglib tagdir="/WEB-INF/tags" prefix="portofino"
%><stripes:layout-definition><%--
--%><!doctype html>
    <html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <jsp:include page="head.jsp"/>
        <stripes:layout-component name="customScripts"/>
        <title>
            <stripes:layout-component name="pageTitle" />
        </title>
    </head>
    <body>
    <jsp:include page="header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="span2 portofino-sidebar">
                <div id="navigation">
                    <ul class="nav nav-list portofino-sidenav">
                        <li class="nav-header">Security</li>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Root permissions"
                                               link="/actions/admin/root-page/permissions"/>
                        <li class="nav-header">Configuration</li>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Settings"
                                               link="/actions/admin/settings"/>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Top-level pages"
                                               link="/actions/admin/root-page/children"/>
                        <li class="nav-header">Data modeling</li>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Wizard"
                                               link="/actions/admin/wizard"/>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Connection providers"
                                               link="/actions/admin/connection-providers"/>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Tables"
                                               link="/actions/admin/tables"/>
                        <stripes:layout-render name="/skins/${skin}/adminLink.jsp"
                                               text="Reload model"
                                               link="/actions/admin/reload-model"/>
                    </ul>
                </div>
            </div>
            <div id="content" class="span10">
                <div class="row-fluid">
                    <mde:sessionMessages/>
                    <stripes:layout-component name="portletHeader">
                        <h3 style="border-bottom: 1px solid #E5E5E5">
                            <stripes:layout-component name="portletTitle" />
                        </h3>
                    </stripes:layout-component>
                    <stripes:layout-component name="portletBody" />
                </div>
            </div>
        </div>
    </div
    <jsp:include page="footer.jsp"/>
    </body>
    </html>
</stripes:layout-definition>