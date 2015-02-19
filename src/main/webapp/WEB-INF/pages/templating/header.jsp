<%@ include file="/WEB-INF/pages/templating/include.jsp"%>


<div data-role="page">
  <!--header-->
  <div id="wrap">
    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <p class="navbar-brand">Ambulance Booking System</p>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li>
              <a href="<%= request.getContextPath() %>/home" class="ui-btn ui-icon-home ui-btn-icon-left">Home</a>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Bookings<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<%= request.getContextPath() %>/ambbooking/addNewBooking">Add Booking</a></li>
                <li><a href="<%= request.getContextPath() %>/ambbooking/displayBookings">Display All Bookings</a></li>
                <li><a href="<%= request.getContextPath() %>/ambbooking/bookingPermission">Booking Permission</a></li>
              </ul>
            </li>
            <li>
                <c:url var="url" value="/j_spring_security_logout"></c:url>
              <security:authorize access="isFullyAuthenticated()">
                <a href="<%request.getContextPath();%>${url}" class="ui-btn ui-icon-search ui-btn-icon-left">Logout</a>
              </security:authorize>
              <security:authorize access="isAnonymous()">
                <a href="<%request.getContextPath();%>/login" class="ui-btn ui-icon-search ui-btn-icon-left">Login</a>
              </security:authorize>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
