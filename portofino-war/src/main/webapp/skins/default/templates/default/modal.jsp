<%@ page import="com.manydesigns.portofino.dispatcher.Dispatch"
%><%@ page import="com.manydesigns.portofino.dispatcher.DispatcherUtil"
%><%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"
%><%@ taglib prefix="mde" uri="/manydesigns-elements"
%><stripes:layout-definition><%--
--%><!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <jsp:include page="../../head.jsp"/>
        <stripes:layout-component name="customScripts"/>
        <jsp:useBean id="actionBean" scope="request" type="net.sourceforge.stripes.action.ActionBean" />
        <%
            Dispatch dispatch = DispatcherUtil.getDispatch(request, actionBean);
            pageContext.setAttribute("dispatch", dispatch);
        %>
        <title>
            <stripes:layout-component name="pageTitle">
                <c:out value="${dispatch.lastPageInstance.page.description}"/>
            </stripes:layout-component>
        </title>
    </head>
    <body>
    <jsp:include page="../../header.jsp"/>
    <div class="container">
        <div class="row">
            <div class="span2">
                <div id="navigation">
                    <jsp:include page="../../navigation.jsp" />
                </div>
            </div>
            <div id="content" class="span10">
                <div class="row-fluid">
                    <mde:sessionMessages/>
                    <div class="pull-right">
                        <jsp:include page="/skins/${skin}/return-to-parent.jsp" />
                    </div>
                    <jsp:include page="/skins/${skin}/breadcrumbs.jsp" />
                    <stripes:layout-component name="portletHeader">
                        <h3 style="border-bottom: 1px solid #E5E5E5">
                            <stripes:layout-component name="portletTitle" />
                        </h3>
                    </stripes:layout-component>
                    <stripes:layout-component name="portletBody" />
                </div>
            </div>
        </div>
        <div class="row">
            <jsp:include page="../../footer.jsp"/>
        </div>
    </div>
    </body>
    </html>
</stripes:layout-definition>