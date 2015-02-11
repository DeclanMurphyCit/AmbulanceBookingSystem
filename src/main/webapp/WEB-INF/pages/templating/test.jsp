









<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/><meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Ambulance Booking System
    Login
  </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/style.css" rel="stylesheet">

</head>

<body>










<div id="header" data-role="page">
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
            <li><a href="#">Home</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Bookings<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/ambbooking/addNewBooking">Add Booking</a></li>
                <li><a href="/ambbooking/displayBookings">Display All Bookings</a></li>
                <li><a href="/ambbooking/displayBooking">Display Booking</a></li>
                <li><a href="/ambbooking/removeBooking">Remove Booking</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li class="right-side"><a href="#">One more separated link</a></li>
              </ul>
            </li>
            <li><a href="#">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
  </div>
</div>









<div id="login-box">



  <form name="f" action="j_spring_security_check" method="POST">

    <div class="ui-field-contain">
      <label for="fullname">Username:</label>
      <input type="text" name="j_username">
    </div>

    <div class="ui-field-contain">
      <label for="fullname">Password:</label>
      <input type="password" name="j_password">
    </div>

    <input name="submit" type="submit" value="Login" />
  </form>
</div>
<div id="footer" data-role="footer" style="text-align:center;" data-position="fixed">
  <div class="container">
    <p align="right" class="muted credit">&copy; Declan Murphy 2015</p>
  </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>