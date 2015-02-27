<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<div id="main-panel" align="center"  style="width:400px;">
    <c:choose>
        <c:when test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:when>
        <c:when test="${not empty msg}">
            <div class="alert alert-success">${msg}</div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">Please enter your username and password to login</div>
        </c:otherwise>
    </c:choose>

  <form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">

    <div class="ui-field-contain">
      <label>Username:</label>
      <input type="text" name="j_username">
    </div>

    <div class="ui-field-contain">
      <label>Password:</label>
      <input type="password" name="j_password">
    </div>

    <input name="submit" type="submit" value="Login" />
  </form>
</div>

<script src="<%= request.getContextPath() %>/resources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/bootstrap/js/bootstrap.min.js"></script>