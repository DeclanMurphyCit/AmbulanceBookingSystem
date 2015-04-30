<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 12/02/2015
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<link href="<c:url value="/WebResources/jqueryui/jquery-uimin.css" />" rel="stylesheet">
<link href="<c:url value="/WebResources/datetimepicker/datepicker.css" />" rel="stylesheet">

<form:form method="post" class="form-horizontal" role="form" id="companyForm" modelAttribute="crewuser" action="addNewCrew">
  <div id="main-panel" align="center" style="width:500px;">
    <h3>${title}</h3>
    <div id="loading" align="center">
      <img src="<c:url value="/WebResources/images/loading_spinner.gif" />" style="width:75px;height:75px">
    </div>
    <div id="loadingComplete" style="display: none;">

      <div class="form-group">
        <spring:bind path="crewuser.ambulanceCrew.crewIdentifier">
        <label  class="control-label col-sm-3" for="${status.expression}">Crew&nbsp;Identifier</label>
        <div class="col-sm-9  ">
          <input class="form-control" type="text" name="${status.expression}" id="${status.expression}" value="${status.value}"><br />
          </spring:bind>
        </div>
      </div>

      <div class="form-group">
        <spring:bind path="crewuser.userObj.userName">
        <label  class="control-label col-sm-3" for="${status.expression}">Username</label>
        <div class="col-sm-9  ">
          <input class="form-control" type="text" name="${status.expression}" id="${status.expression}" value="${status.value}"><br />
          </spring:bind>
        </div>
      </div>

      <div class="form-group">
        <spring:bind path="crewuser.userObj.password">
        <label  class="control-label col-sm-3" for="${status.expression}">Password</label>
        <div class="col-sm-9  ">
          <input class="form-control" type="password" name="${status.expression}" id="${status.expression}" value="${status.value}"><br />
          </spring:bind>
        </div>
      </div>

      <div class="form-group">
        <input type="submit" data-icon="check" value="Submit"/>
      </div>

    </div>
  </div>
</form:form>

<script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/jqueryui/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/datetimepicker/jquery.datetimepicker.js"></script>
<script src="<%= request.getContextPath() %>/WebResources/form-validator/jquery.form-validator.min.js"></script>

<script>
  jQuery(document).ready(function () {
    jQuery("#loading").hide();
    jQuery("#loadingComplete").show();
  });
</script>