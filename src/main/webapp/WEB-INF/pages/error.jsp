<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 12/02/2015
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>
<div id="main-panel" style="width: 500px;" align="center">
    <h5>${message}</h5>
</div>

<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>