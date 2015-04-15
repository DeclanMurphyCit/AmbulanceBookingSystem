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


<form:form method="post" class="form-horizontal" id="companyForm" action="addNewCompany" modelAttribute="ambulancecompany">
  <div id="main-panel" align="center" style="width:500px;">
    <div id="loading" align="center">
      <img src="/WebResources/images/loading_spinner.gif" style="width:75px;height:75px">
    </div>
    <div id="loadingComplete" style="display: none;">
      <form:errors path="*" cssClass="errorblock" element="div" />

      <div class="form-group">
        <label class="control-label col-xs-2" for="wardId">Ward</label>
        <div class="col-xs-10">
          <select class="form-control" id="wardId" required="required" onchange="getWardList()">
            <option value="" label=""/>
            <c:forEach var="ward" items="${wardList}">
              <option value="${ward.id}">${ward.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>

      <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
          <input type="submit" data-icon="check" value="Submit"/>
        </div>
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