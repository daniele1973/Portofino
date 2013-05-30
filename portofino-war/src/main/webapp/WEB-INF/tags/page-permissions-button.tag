<%@ tag import="com.manydesigns.portofino.logic.SecurityLogic"
%><%@ tag import="com.manydesigns.portofino.security.AccessLevel"
%><%@ tag import="org.apache.shiro.SecurityUtils"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><jsp:useBean id="actionBean" scope="request" type="com.manydesigns.portofino.pageactions.AbstractPageAction"/>
<% if(SecurityLogic.hasPermissions(actionBean.getPageInstance(), SecurityUtils.getSubject(), AccessLevel.DEVELOP)) { %>
<button name="pagePermissions"
        type="submit" class="btn btn-mini" role="button" aria-disabled="false"
        title="Page permissions">
    <i class="icon-user"></i>
</button>
<% } %>