









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

<body role="document">










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

                            <a href="/home" class="ui-btn ui-icon-home ui-btn-icon-left">Home</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Bookings<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/ambbooking/addNewBooking">Add Booking</a></li>
                                <li><a href="/ambbooking/displayBookings">Display All Bookings</a></li>
                                <li><a href="/ambbooking/displayBooking">Display Booking</a></li>
                                <li><a href="/ambbooking/removeBooking">Remove Booking</a></li>
                            </ul>
                        </li>
                        <li>


                            <a href="/login" class="ui-btn ui-icon-search ui-btn-icon-left">Login</a>

                        </li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>










        <div id="main-panel" align="center">



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
    </div>
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