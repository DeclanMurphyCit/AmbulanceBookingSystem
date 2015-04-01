<%--
  Created by IntelliJ IDEA.
  User: Declan
  Date: 17/02/2015
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/templating/include.jsp"%>

<style>
    <% //Check if browser is on a computer
String ua=request.getHeader("User-Agent").toLowerCase();
if(!ua.matches("(?i).*((android|bb\\d+|meego).+mobile|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\\.(browser|link)|vodafone|wap|windows ce|xda|xiino).*")||ua.substring(0,4).matches("(?i)1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\\-|your|zeto|zte\\-")) {
%>
    .col-md-9{
        width: 65%;
        text-align: left;
    }

    .col-md-2{
        width: 35%;
        text-align: right;
        font-weight: bold;
    }
</style>

<div id="main-panel" align="center" style="width:500px;">
    <h2>${message}</h2>

    <h3><span id="numBookings" class="label label-warning">Number of pending bookings: ${numberOfBookings}</span></h3>

    <c:forEach var="ab" items="${bookings}">
    <c:choose>
    <c:when test="${ab.urgent == true}">
    <div class="alert alert-danger panel-body" role="alert" id="booking-${ab.bookingId}">
        </c:when>
        <c:otherwise>
        <div id="booking-${ab.bookingId}"  class="panel-body alert-info">
            </c:otherwise>
            </c:choose>



                <div class="col-md-2" align="right">Patient:</div>
                <div class="col-md-9" align="left"><c:out value="${patients[ab.bookingId]}"/></div>

                <div class="col-md-2" align="right">Origin:</div>
                <div class="col-md-9" align="left">
                    <c:forEach var="location" items="${locations}">
                        <c:if test="${ab.origin == location.id}">
                            ${ location.name}
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-2" align="right">Destination:</div>
                <div class="col-md-9" align="left">
                    <c:forEach var="location" items="${locations}">
                        <c:if test="${ab.destination == location.id}">
                            ${ location.name}
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-2" align="right">Cardiac:</div>
                <div class="col-md-9" align="left">
                    <c:choose>
                        <c:when test="${ab.cardiac == true}">
                            Yes
                        </c:when>
                        <c:otherwise>
                            No
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-2" align="right">Urgent:</div>
                <div class="col-md-9" align="left">
                    <c:choose>
                        <c:when test="${ab.urgent == true}">
                            Yes
                        </c:when>
                        <c:otherwise>
                            No
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-2" align="right">Transfer Date:</div>
                <div class="col-md-9" align="left"> ${ab.dateOfTransfer} </div>

                <div class="col-md-2" align="right">Date Created:</div>
                <div class="col-md-9" align="left"> ${ab.dateCreated} </div>

                <div class="col-md-2" align="right">Created By:</div>
                <div class="col-md-9" align="left"> ${users[ab.bookingId]} </div>

                <div class="col-md-7" style="margin-top: 15px">
                    <button class="btn btn-success" style="width: 75px;" id="accept-${ab.bookingId}" onclick="setBookingApproval(${ab.bookingId},'accept')">Accept</button>
                </div>
                <div class="col-md-4" style="margin-top: 15px">
                    <button class="btn btn-danger" style="width: 75px;" id="deny-${ab.bookingId}" onclick="setBookingApproval(${ab.bookingId},'deny')">Deny</button>
                </div>


        </div>
        <hr>
        </c:forEach>
    </div>
        <% } else { //if the browser is on a mobile phone %>

    <style>
        .col-xs-7{
            margin: 0 -25px;
        }

        .buttonsRow{

            margin-left: auto;
            margin-right: auto;
            width: inherit;
            margin-top: 15px;
        }

        .alert-danger, .alert-info{
            margin-left: 3px;
        }

        .bold-text{
            font-weight: bold;
            margin: 0 -25px;
        }
    </style>


    <div align="center">
        <h3><span id="numBookings"  class="label label-warning">Number of pending bookings: ${numberOfBookings}</span></h3>
    </div>
    <c:forEach var="ab" items="${bookings}">
    <c:choose>
    <c:when test="${ab.urgent == true}">
    <div class="alert alert-danger panel-body" role="alert" id="booking-${ab.bookingId}">
        </c:when>
        <c:otherwise>
        <div class="panel-body alert-info" id="booking-${ab.bookingId}">
            </c:otherwise>
            </c:choose>

            <div >

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Patient:</div>
                    <div class="col-xs-7" align="left"><c:out value="${patients[ab.bookingId]}"/></div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Origin:</div>
                    <div class="col-xs-7" align="left">
                        <c:forEach var="location" items="${locations}">
                            <c:if test="${ab.origin == location.id}">
                                ${ location.name}
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Destination:</div>
                    <div class="col-xs-7" align="left">
                        <c:forEach var="location" items="${locations}">
                            <c:if test="${ab.destination == location.id}">
                                ${ location.name}
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Cardiac:</div>
                    <div class="col-xs-7" align="left">
                        <c:choose>
                            <c:when test="${ab.cardiac == true}">
                                Yes
                            </c:when>
                            <c:otherwise>
                                No
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Urgent:</div>
                    <div class="col-xs-7" align="left">
                        <c:choose>
                            <c:when test="${ab.urgent == true}">
                                Yes
                            </c:when>
                            <c:otherwise>
                                No
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Transfer Date:</div>
                    <div class="col-xs-7" align="left"> ${ab.dateOfTransfer} </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Date Created:</div>
                    <div class="col-xs-7" align="left"> ${ab.dateCreated} </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 bold-text" align="right">Created By:</div>
                    <div class="col-xs-7" align="left"> ${users[ab.bookingId]} </div>
                </div>

                <div class="buttonsRow">
                    <div class="col-xs-6" style="margin-top: 15px">
                        <button class="btn btn-success" style="width: 130px;" id="accept-${ab.bookingId}" onclick="setBookingApproval(${ab.bookingId},'accept')">Accept</button>
                    </div>
                    <div class="col-xs-6" style="margin-top: 15px">
                        <button class="btn btn-danger" style="width: 130px;" id="deny-${ab.bookingId}" onclick="setBookingApproval(${ab.bookingId},'deny')">Deny</button>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        </c:forEach>

            <%}%>

        <script src="<%= request.getContextPath() %>/WebResources/jquery-1.11.2.min.js"></script>
        <script src="<%= request.getContextPath() %>/WebResources/bootstrap/js/bootstrap.min.js"></script>

        <script type="text/javascript">
            var bookingIdArray = [ ${bookingIdArray}];
            var numBookings = ${numberOfBookings};
            var numBookingsUpdated = 0;

            function setBookingApproval(bookingId,approval) {
                jQuery.ajax({
                    type: "POST",
                    url: '<%=request.getContextPath()%>/ambbooking/'+approval+'Booking',
                    data: ({bookingId : bookingId}),
                    success: function(data) {
                        if(data == "success")
                        {
                            jQuery('#booking-'+bookingId).slideUp(300, function() { jQuery(this).remove(); });
                            numBookings--;
                            if(numBookings ==0)
                            {
                                jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");
                                jQuery("#numBookings").text("No bookings require approval");
                            }
                            else
                                jQuery("#numBookings").text("Number of pending bookings: " +numBookings);

                        } else if ( data == "notLoggedIn")
                        {
                            alert("Please login before approving a booking");
                        }
                    },
                    error: function(e){
                        alert('Error: ' + e);
                    }
                });
            }

            jQuery(document).ready(function () {

                if(numBookings ==0)
                {
                    jQuery("#numBookings").removeClass("label-warning").addClass("label-primary");
                    jQuery("#numBookings").html("No bookings require approval");
                }


                setInterval(function() {
                    numBookingsUpdated = 0;

                    jQuery.ajax({
                        type: "GET",
                        dataType: 'json',
                        url: '<%=request.getContextPath()%>/ambbooking/getNewUnapprovedBookings',
                        success: function (data) {
                            if (data != "({})") {
                                jQuery.each(data, function(index, element) {
                                    numBookingsUpdated++;
                                    if(arrayContains(element.bookingId)== false) //if an id is not in the array
                                        location.reload();
                                });
                            }

                            if(numBookings != numBookingsUpdated)
                                location.reload();

                        },
                        error: function (e) {
                            console.log("error" + e);
                        }
                    });



                }, 2000);
            });

            function arrayContains(id)
            {
                return (bookingIdArray.indexOf(id) > -1);
            }

        </script>