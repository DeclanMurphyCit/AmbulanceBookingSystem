<%@ include file="/WEB-INF/pages/templating/include.jsp"%>
  <div id="main-panel" align="center" style="width:500px;">
    <h3>Welcome page test</h3>
    <h5>${message}</h5>
  </div>

<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>