<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Ambulance Booking System
    <tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
  </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<%--  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>--%>
</head>

<body role="document">
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</body>
</html>