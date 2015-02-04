<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 04/02/2015
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/pages/include.jsp"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>CIT Online
    <tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
  </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.css">
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.js"></script>
  <!-- <link href="<c:url value="/resources/css/citonline.css" />" rel="stylesheet">  -->
  <style>
    .msg {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #31708f;
      background-color: #d9edf7;
      border-color: #bce8f1;
    }

    #login-box {
      width: 600px;
      padding: 20px;
      margin: 100px auto;
      background: #fff;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border: 1px solid #000;
    }
    th {
      border-bottom: 1px solid #d6d6d6;
    }

    tr:nth-child(even) {
      background: #e9e9e9;
    }
  </style>
</head>

<body>
<div data-role="page">
  <!--header-->
  <div data-role="header" data-position="fixed" data-theme="a" >
    <tiles:insertAttribute name="header"  />
  </div>
  <!--main body with a panel to the left -->
  <div data-role="main" class="ui-content" id="pageone">
    <a href="#myPanelDefault" class="ui-btn ui-btn-inline ui-corner-all ui-shadow">Menu Options</a>
    <tiles:insertAttribute name="body" />
  </div>

  <!--footer-->
  <div  data-role="footer" data-position="fixed" data-theme="a">
    <tiles:insertAttribute name="footer" />
  </div>


  <!-- Panel -->
  <div data-role="panel" id="myPanelDefault" style="text-align:center;">
    <div data-role="collapsibleset">

      <security:authorize access="hasRole('ROLE_STUDENT')">
        <h1 data-icon="plus">Menu</h1>
        <ul data-role="listview" data-inset="true">
          <li><a href="<%= request.getContextPath() %>/deferral/addNewDeferral">Add Deferral</a></li>
        </ul>
      </security:authorize>
    </div>

    <a href="#pageone" data-rel="close" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-left">Close Menu</a>
  </div>
</div>
</body>
</html>
