<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<style>
    .navbar-default {
        background-color: #0058ce;
        border-color: #003f92;
    }
    .navbar-default .navbar-brand {
        color: #ffffff;
    }
    .navbar-default .navbar-brand:hover, .navbar-default .navbar-brand:focus {
        color: #000000;
    }
    .navbar-default .navbar-text {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > a {
        color: #ffffff;
    }
    .navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus {
        color: #000000;
    }
    .navbar-default .navbar-nav > .active > a, .navbar-default .navbar-nav > .active > a:hover, .navbar-default .navbar-nav > .active > a:focus {
        color: #000000;
        background-color: #003f92;
    }
    .navbar-default .navbar-nav > .open > a, .navbar-default .navbar-nav > .open > a:hover, .navbar-default .navbar-nav > .open > a:focus {
        color: #000000;
        background-color: #003f92;
    }
    .navbar-default .navbar-toggle {
        border-color: #003f92;
    }
    .navbar-default .navbar-toggle:hover, .navbar-default .navbar-toggle:focus {
        background-color: #003f92;
    }
    .navbar-default .navbar-toggle .icon-bar {
        background-color: #ffffff;
    }
    .navbar-default .navbar-collapse,
    .navbar-default .navbar-form {
        border-color: #ffffff;
    }
    .navbar-default .navbar-link {
        color: #ffffff;
    }
    .navbar-default .navbar-link:hover {
        color: #000000;
    }

    @media (max-width: 767px) {
        .navbar-default .navbar-nav .open .dropdown-menu > li > a {
            color: #ffffff;
        }
        .navbar-default .navbar-nav .open .dropdown-menu > li > a:hover, .navbar-default .navbar-nav .open .dropdown-menu > li > a:focus {
            color: #000000;
        }
        .navbar-default .navbar-nav .open .dropdown-menu > .active > a, .navbar-default .navbar-nav .open .dropdown-menu > .active > a:hover, .navbar-default .navbar-nav .open .dropdown-menu > .active > a:focus {
            color: #000000;
            background-color: #003f92;
        }
    }
</style>

<div data-role="page">
    <!--header-->
    <div id="wrap">
        <c:url var="url" value="/j_spring_security_logout"></c:url>
            <% //Check if mobile
String ua=request.getHeader("User-Agent").toLowerCase();
if(!ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
%>
        <!-- Fixed navbar -->

        <nav class="navbar navbar-default navbarsurround">

            <div class="container-default">

                <div style="alignment: left" class="navbar-header navbarsurround">
                    <p class="navbar-text">&nbsp;&nbsp;&nbsp;&nbsp;
                        <img style="margin: -15px" src="<c:url value='/WebResources/images/ambulance_car-48.png' />"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <b>Ambulance Booking System</b></p>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <security:authorize access="isFullyAuthenticated()">

                            <c:if test="${(alert == 'adon') || (alert == 'ambCompany') }">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                        <img id="notificationImage" style="margin: -15px; margin-right: -20px;" src="<c:url value='/WebResources/images/alert-icon-red.png' />" width="52px" height="48px"/>
                                        &nbsp;&nbsp;Pending Notification<span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="icon-bar">
                                            <security:authorize access="hasAnyRole('ROLE_ADON')">
                                                <a href="<%= request.getContextPath() %>/ambbooking/bookingPermission">
                                                    An ambulance booking is currently pending permission
                                                </a>
                                            </security:authorize>
                                            <security:authorize access="hasAnyRole('ROLE_AMB_COMP')">
                                                <a href="<%= request.getContextPath() %>/ambcompany/bookingStandby">
                                                    An ambulance booking currently has no crew assigned to it
                                                </a>
                                            </security:authorize>
                                        </li>
                                    </ul>
                                </li>
                                <script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
                                <script type="text/javascript">

                                    function blink(){
                                        jQuery('#notificationImage').delay(333).fadeTo(100,0.5).delay(333).fadeTo(100,1, blink);
                                    }

                                    jQuery(document).ready(function () {
                                        blink(1000, 750);
                                    });

                                </script>

                            </c:if>

                        <li>
                            <a href="<%= request.getContextPath() %>/home" class="ui-btn ui-icon-home ui-btn-icon-left">Home</a>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Ambulance Bookings<span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <security:authorize access="hasAnyRole('ROLE_NURSE','ROLE_ADMIN','ROLE_ADON')">
                                    <li><a href="<%= request.getContextPath() %>/ambbooking/addNewBooking">Add Booking</a></li>
                                </security:authorize>
                                <security:authorize access="hasAnyRole('ROLE_NURSE','ROLE_ADMIN','ROLE_ADON','ROLE_AMB_COMP')">
                                    <li><a href="<%= request.getContextPath() %>/ambbooking/displayBookings">Display All Bookings</a></li>
                                </security:authorize>
                                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ADON')">
                                    <li><a href="<%= request.getContextPath() %>/ambbooking/bookingPermission">Booking Permission</a></li>
                                </security:authorize>
                                <security:authorize access="hasAnyRole('ROLE_AMB_COMP')">
                                    <li><a href="<%= request.getContextPath() %>/ambcompany/bookingStandby">Booking Standby</a></li>
                                    <li><a href="<%= request.getContextPath() %>/admin/addNewCrew">Add Crew</a></li>
                                </security:authorize>
                                <security:authorize access="hasAnyRole('ROLE_AMB_CREW')">
                                    <li><a href="<%= request.getContextPath() %>/ambcompany/ambCrewApp">Ambulance Crew App</a></li>
                                </security:authorize>
                            </ul>
                        </li>
                        <li>
                            <a href="<%request.getContextPath();%>${url}" class="ui-btn ui-icon-search ui-btn-icon-left">Logout</a>
                            </security:authorize>
                            <security:authorize access="isAnonymous()">
                        <li>
                            <a href="<%request.getContextPath();%>/AmbulanceBookingSystem/login" class="ui-btn ui-icon-search ui-btn-icon-left">Login</a>
                            </security:authorize>
                        </li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
            <%
  } else {

%>


        <%--        <button class="btn btn-navbar" style="width: 48%;" onclick="window.location.href='<%request.getContextPath();%>/AmbulanceBookingSystem/home'">Home</button>--%>


            <%
    }
%>