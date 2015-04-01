<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Ambulance Booking System
    <tiles:insertAttribute name="title" ignore="true"/>
  </title>
    <link rel="icon" type="image/ico" href="<c:url value="/WebResources/images/favicon.ico" />">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<c:url value="/WebResources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/WebResources/css/style.css" />" rel="stylesheet">
</head>

<body role="document">
    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>
    <tiles:insertAttribute name="footer"/>
</body>
</html>