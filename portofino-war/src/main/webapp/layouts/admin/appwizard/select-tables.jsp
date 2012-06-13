<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"
%><%@ taglib prefix="mde" uri="/manydesigns-elements"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><%@ taglib tagdir="/WEB-INF/tags" prefix="portofino" %>
<stripes:layout-render name="/skins/default/admin-page.jsp">
    <jsp:useBean id="actionBean" scope="request" type="com.manydesigns.portofino.actions.admin.appwizard.ApplicationWizard"/>
    <stripes:layout-component name="pageTitle">
        Step 3. Customize
    </stripes:layout-component>
    <stripes:layout-component name="contentHeader">
        <portofino:buttons list="select-tables" cssClass="contentButton" />
    </stripes:layout-component>
    <stripes:layout-component name="portletTitle">
        Step 3. Customize
    </stripes:layout-component>
    <stripes:layout-component name="portletBody">
        <mde:sessionMessages />
        <mde:write name="actionBean" property="userAndGroupTablesForm"/>
        <c:if test="${actionBean.advanced}">
            <h3>Select roots</h3>
            <mde:write name="actionBean" property="rootsForm"/>
        </c:if>
        <div style="display: none;">
            <c:if test="${!actionBean.advanced}">
                <mde:write name="actionBean" property="rootsForm"/>
            </c:if>
            <mde:write name="actionBean" property="schemasForm"/>
            <input type="hidden" name="connectionProviderType" value="${actionBean.connectionProviderType}" />
            <input type="hidden" name="advanced" value="${actionBean.advanced}" />
            <mde:write name="actionBean" property="jndiCPForm"/>
            <mde:write name="actionBean" property="jdbcCPForm"/>
        </div>
    </stripes:layout-component>
    <stripes:layout-component name="contentFooter">
        <portofino:buttons list="select-tables" cssClass="contentButton" />
    </stripes:layout-component>
</stripes:layout-render>